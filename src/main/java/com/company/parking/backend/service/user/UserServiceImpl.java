package com.company.parking.backend.service.user;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.parking.backend.dto.AuthenticationResponse;
import com.company.parking.backend.dto.IUserProjection;
import com.company.parking.backend.model.User;
import com.company.parking.backend.model.dao.IUserDao;
import com.company.parking.backend.repository.IUserRepository;
import com.company.parking.backend.response.user.UserResponseRest;

@Service("userService")
public class UserServiceImpl implements IUserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IUserRepository userRepository; // this is a more customizable dao

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<UserResponseRest> findUser() {
		log.info("findUser method started");

		UserResponseRest response = new UserResponseRest();

		try {
			List<User> user = (List<User>) userDao.findAll();
			response.getUserReponse().setUser(user);
			response.setMetadata("ok", "00", "Success");
		} catch (Exception e) {
			response.setMetadata("nok", "-1", "Error");
			log.error("There was an error with the query", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<UserResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK); // 200
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<AuthenticationResponse> authenticateUser(String email, String password) {
		AuthenticationResponse response = new AuthenticationResponse();

		try {
			List<IUserProjection> users = userRepository.findByEmailAndPassword(email, password);
			if (!users.isEmpty()) {
				response.setEmail(users.get(0).getEmail());
				response.setName(users.get(0).getName());
				response.setMetadata("ok", "00", "Success");
				return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.OK);
			} else {
				response.setMetadata("nok", "01", "Email or password incorrect");
				return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			response.setMetadata("nok", "-1", "Error");
			log.error("There was an error with the query", e);
			e.getStackTrace();
			return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

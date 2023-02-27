package com.company.parking.backend.service.user;

import org.springframework.http.ResponseEntity;

import com.company.parking.backend.dto.AuthenticationResponse;
import com.company.parking.backend.response.user.UserResponseRest;

public interface IUserService {
	public ResponseEntity<UserResponseRest> findUser();

	ResponseEntity<AuthenticationResponse> authenticateUser(String email, String password);
}

package com.company.parking.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.parking.backend.dto.AuthenticationRequest;
import com.company.parking.backend.dto.AuthenticationResponse;
import com.company.parking.backend.response.user.UserResponseRest;
import com.company.parking.backend.service.user.IUserService;

@RestController
@RequestMapping("/v1")
public class UserRestController {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	@GetMapping("/users")
	public ResponseEntity<UserResponseRest> findAll() {
		ResponseEntity<UserResponseRest> response = userService.findUser();
		return response;
	}

	@PostMapping("/auth")
	public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest request) {
		String email = request.getEmail();
		String password = request.getPassword();
		return userService.authenticateUser(email, password);
	}

}

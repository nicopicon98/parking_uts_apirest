package com.company.parking.backend.response.user;

import com.company.parking.backend.response.ResponseRest;

public class UserResponseRest extends ResponseRest {

	private UserResponse userReponse = new UserResponse();

	public UserResponse getUserReponse() {
		return userReponse;
	}

	public void setUserReponse(UserResponse userReponse) {
		this.userReponse = userReponse;
	}

}

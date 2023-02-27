package com.company.parking.backend.response.user;

import java.util.List;

import com.company.parking.backend.model.User;

public class UserResponse {
	private List<User> user;

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

}

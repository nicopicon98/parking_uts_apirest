package com.company.parking.backend.dto;

public class AuthenticationRequest {
	private String email;
	private String password;

	// Constructor vacío requerido para que Jackson pueda deserializar la solicitud
	public AuthenticationRequest() {
	}

	// Constructor con argumentos para inicializar la solicitud
	public AuthenticationRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	// Getters y setters para las propiedades de la solicitud
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

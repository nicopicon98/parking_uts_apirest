package com.company.parking.backend.response.vehicle;

import java.time.LocalDateTime;
import com.company.parking.backend.response.ResponseRest;

public class VehicleResponse extends ResponseRest {

	private Long id;
	private String name;
	private String plate;
	private String type;
	private LocalDateTime checkIn;
	private int state;

	public VehicleResponse(Long id, String name, String plate, String type, LocalDateTime checkIn, int state) {
		this.id = id;
		this.name = name;
		this.plate = plate;
		this.type = type;
		this.checkIn = checkIn;
		this.state = state;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDateTime checkIn) {
		this.checkIn = checkIn;
	}

}

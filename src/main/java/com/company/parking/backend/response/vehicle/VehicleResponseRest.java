package com.company.parking.backend.response.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.parking.backend.model.Vehicle;
import com.company.parking.backend.response.ResponseRest;

public class VehicleResponseRest extends ResponseRest {
	private Map<String, Object> vehicleResponse;

	public VehicleResponseRest() {

	}

	public VehicleResponseRest(List<Vehicle> vehicles, String all) {
		List<Map<String, Object>> vehicleList = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			Map<String, Object> vehicleMap = new HashMap<>();
			vehicleMap.put("id", vehicle.getId());
			vehicleMap.put("name", vehicle.getName());
			vehicleMap.put("plate", vehicle.getPlate());
			vehicleMap.put("type", vehicle.getType());
			vehicleMap.put("check_in", vehicle.getCheckIn());
			vehicleMap.put("check_out", vehicle.getCheckOut());
			vehicleMap.put("state", vehicle.getState());
			vehicleList.add(vehicleMap);
		}
		this.vehicleResponse = new HashMap<>();
		this.vehicleResponse.put("vehicle", vehicleList);
	}
	
	public VehicleResponseRest(List<Vehicle> vehicles) {
		List<Map<String, Object>> vehicleList = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			Map<String, Object> vehicleMap = new HashMap<>();
			vehicleMap.put("id", vehicle.getId());
			vehicleMap.put("name", vehicle.getName());
			vehicleMap.put("plate", vehicle.getPlate());
			vehicleMap.put("type", vehicle.getType());
			vehicleMap.put("check_in", vehicle.getCheckIn());
			vehicleMap.put("state", vehicle.getState());
			vehicleList.add(vehicleMap);
		}
		this.vehicleResponse = new HashMap<>();
		this.vehicleResponse.put("vehicle", vehicleList);
	}

	public Map<String, Object> getVehicleResponse() {
		return vehicleResponse;
	}

	public void setVehicleResponse(Map<String, Object> vehicleResponse) {
		this.vehicleResponse = vehicleResponse;
	}
}
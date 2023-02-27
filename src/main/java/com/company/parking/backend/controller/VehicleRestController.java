package com.company.parking.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.parking.backend.dto.VehicleCreateRequest;
import com.company.parking.backend.response.vehicle.VehicleResponseRest;
import com.company.parking.backend.service.vehicle.IVehicleService;

@RestController
@RequestMapping("/v1")
public class VehicleRestController {

	@Autowired
	@Qualifier("vehicleService")
	private IVehicleService vehicleService;

	@GetMapping("/listAllVehicles")
	public ResponseEntity<VehicleResponseRest> findAll() {
		ResponseEntity<VehicleResponseRest> response = vehicleService.findVehicle();
		return response;
	}

	@GetMapping("/listVehicleById/{id}")
	public ResponseEntity<VehicleResponseRest> findById(@PathVariable Long id) {
		ResponseEntity<VehicleResponseRest> response = vehicleService.findVehicleById(id);
		return response;
	}

	@PostMapping("/insertVehicle")
	public ResponseEntity<VehicleResponseRest> create(@RequestBody VehicleCreateRequest vehicle) {
		ResponseEntity<VehicleResponseRest> response = vehicleService.InsertVehicle(vehicle);
		return response;
	}

	@PutMapping("/updateVehicleById/{id}")
	public ResponseEntity<VehicleResponseRest> updateById(@PathVariable Long id) {
		ResponseEntity<VehicleResponseRest> response = vehicleService.updateVehicleById(id);
		return response;
	}
}

package com.company.parking.backend.service.vehicle;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.parking.backend.dto.VehicleCreateRequest;
import com.company.parking.backend.response.vehicle.VehicleResponseRest;

@Service("vehicleService")
public interface IVehicleService {
	public ResponseEntity<VehicleResponseRest> findVehicle();

	public ResponseEntity<VehicleResponseRest> findVehicleById(Long id);

	public ResponseEntity<VehicleResponseRest> updateVehicleById(Long id);

	public ResponseEntity<VehicleResponseRest> InsertVehicle(VehicleCreateRequest vehicle);
}

package com.company.parking.backend.service.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.company.parking.backend.dto.VehicleCreateRequest;
import com.company.parking.backend.model.Vehicle;
import com.company.parking.backend.repository.IVehicleRepository;
import com.company.parking.backend.response.vehicle.VehicleResponseRest;

@Service("vehicleService")
public class VehicleServiceImpl implements IVehicleService {

	private static final Logger log = LoggerFactory.getLogger(VehicleServiceImpl.class);

	@Autowired
	private IVehicleRepository vehicleRepository; // this is a more customizable dao

	@Override
	public ResponseEntity<VehicleResponseRest> findVehicle() {
		// started method
		log.info("method findVehicle() started");
		//initialized the object
		VehicleResponseRest response;
		try {
			List<Vehicle> vehicleResponses = vehicleRepository.findAllByState(0); // fetch the query response
			response = new VehicleResponseRest(vehicleResponses); // adapter
			response.setMetadata("ok", "00", "Success");
			return new ResponseEntity<VehicleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			// handle any exceptions that may occur during the vehicle retrieval process
			response = new VehicleResponseRest();
			response.setMetadata("nok", "-1", "Error al recuperar los vehículos de la base de datos");
			return new ResponseEntity<VehicleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<VehicleResponseRest> findVehicleById(Long id) {
		// started method
		log.info("method findVehicleById() started");
		// response expected always
		VehicleResponseRest response;
		// this is to match the type required by VehicleResponseRest
		List<Vehicle> vehicleResponseRest = new ArrayList<Vehicle>();
		try {
			Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);

			if (!vehicleOptional.isPresent()) {
				// If a matching vehicle is not found, return a 404 Not Found status code
				response = new VehicleResponseRest();
				response.setMetadata("nok", "-2", "No se encontró ningún vehículo con el ID proporcionado");
				return new ResponseEntity<VehicleResponseRest>(response, HttpStatus.NOT_FOUND);
			}

			// If a matching vehicle is found, create a response object with the vehicle
			// data
			Vehicle vehicle = vehicleOptional.get();
			vehicleResponseRest.add(vehicle);
			response = new VehicleResponseRest(vehicleResponseRest);
			response.setMetadata("ok", "00", "Exito");
			return new ResponseEntity<VehicleResponseRest>(response, HttpStatus.OK);
		} catch (Exception e) {
			// handle any exceptions that may occur during the vehicle retrieval process
			response = new VehicleResponseRest();
			response.setMetadata("nok", "-1", "Error al recuperar el vehículo de la base de datos");
			return new ResponseEntity<VehicleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<VehicleResponseRest> updateVehicleById(Long id) {
		// started the method
		log.info("method updateVehicleById() started");
		// initialize response
		VehicleResponseRest response;
		// this is to match the type required by VehicleResponseRest
		List<Vehicle> vehicleResponseRest = new ArrayList<Vehicle>();
		try {

			Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);

			if (!vehicleOptional.isPresent()) {
				// If a matching vehicle is not found, return a 404 Not Found status code
				response = new VehicleResponseRest();
				response.setMetadata("nok", "-1", "No se encontró ningún vehículo con el ID proporcionado");
				return new ResponseEntity<VehicleResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			if(vehicleOptional.get().getState() != 0) {
				// If a matching vehicle is found, but state is != 0 then there is a mismatch
				response = new VehicleResponseRest();
				response.setMetadata("nok", "-2", "El vehiculo que intentas marcar estado a SALIDA no se encuentra en el parqueadero");
				return new ResponseEntity<VehicleResponseRest>(response, HttpStatus.NOT_FOUND);
			}

			// If a matching vehicle is found, update the checkOut timestamp and state of
			// the vehicle
			Vehicle vehicle = vehicleOptional.get();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			LocalDateTime localDateTime = timestamp.toLocalDateTime();
			vehicle.setCheckOut(localDateTime); // set the checkOut timestamp to the current time
			vehicle.setState(1); // set the state to 1 (unavailable)
			vehicleRepository.save(vehicle); // save the updated vehicle to the database
			vehicleResponseRest.add(vehicle);
			// create a response object with the updated vehicle data
			response = new VehicleResponseRest(vehicleResponseRest, "all"); // we access a different constructor to map								// differently, this time with checkOut
			response.setMetadata("ok", "00", "Vehículo marcado a estado SALIDA exitosamente");
			return new ResponseEntity<VehicleResponseRest>(response, HttpStatus.OK);
		} catch (Exception e) {
			// handle any exceptions that may occur during the vehicle update process
			response = new VehicleResponseRest();
			response.setMetadata("nok", "-1", "Error al actualizar el vehículo en la base de datos");
			return new ResponseEntity<VehicleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<VehicleResponseRest> InsertVehicle(VehicleCreateRequest request) {
		// started method
		log.info("method InsertVehicle() started");
		VehicleResponseRest response;
		try {
			// Check if the vehicle already exists checking plate and
			List<Vehicle> existingVehicles = vehicleRepository.findAllByPlateAndState(request.getPlate(), 0);
			if (!existingVehicles.isEmpty()) {
				// If a matching vehicle is found, return a 409 Conflict status code
				response = new VehicleResponseRest();
				response.setMetadata("nok", "-1", "Vehículo aún no ha marcado a estado SALIDA");
				return new ResponseEntity<VehicleResponseRest>(response, HttpStatus.CONFLICT);
			}

			// If it's a totally new Vehicle, then continue
			List<Vehicle> vehicleResponseRest = new ArrayList<Vehicle>();

			// create a new Vehicle object
			Vehicle vehicle = new Vehicle();

			// populate the Vehicle object with data from the request
			vehicle.setName(request.getName());
			vehicle.setPlate(request.getPlate());
			vehicle.setType(request.getType());
			vehicle.setState(0); // assuming 0 represents a vehicle that hasn't marked as left

			Vehicle savedVehicle = (Vehicle) vehicleRepository.save(vehicle); // explicit cast to Vehicle
			// create a response object with the saved vehicle data
			vehicleResponseRest.add(savedVehicle);
			response = new VehicleResponseRest(vehicleResponseRest);
			response.setMetadata("ok", "00", "Vehiculo ingresado con exito");
			return new ResponseEntity<VehicleResponseRest>(response, HttpStatus.OK);
		} catch (Exception e) {
			// handle any exceptions that may occur during the Vehicle entity insertion
			// process
			response = new VehicleResponseRest();
			response.setMetadata("nok", "-1", "Error al insertar el vehículo en la base de datos");
			return new ResponseEntity<VehicleResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}

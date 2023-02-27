package com.company.parking.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.parking.backend.model.Vehicle;

public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {
	List<Vehicle> findAllByPlateAndState(String plate, int i);

	List<Vehicle> findAllByState(int i);
}

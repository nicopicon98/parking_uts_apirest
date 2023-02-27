package com.company.parking.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.parking.backend.dto.IUserProjection;
import com.company.parking.backend.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	List<IUserProjection> findByEmailAndPassword(String email, String password);
}
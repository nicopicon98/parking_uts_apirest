package com.company.parking.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.parking.backend.model.User;

public interface IUserDao extends CrudRepository<User, Long> {

}

package com.company.transport.backend.apiresttransport.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.transport.backend.apiresttransport.model.entity.Driver;

public interface DriverDao extends CrudRepository<Driver, Integer>{

}

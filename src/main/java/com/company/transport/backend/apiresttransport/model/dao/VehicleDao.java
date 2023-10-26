package com.company.transport.backend.apiresttransport.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.transport.backend.apiresttransport.model.entity.Vehicle;

/**
 * Interfaz que define un repositorio de datos para la entidad Vehicle.
 * Extiende JpaRepository para aprovechar las operaciones CRUD proporcionadas por Spring Data JPA.
 */
public interface VehicleDao extends JpaRepository<Vehicle, Integer> {
	
	
	
}

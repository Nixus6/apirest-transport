package com.company.transport.backend.apiresttransport.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.company.transport.backend.apiresttransport.model.dao.DriverDao;
import com.company.transport.backend.apiresttransport.model.dao.VehicleDao;
import com.company.transport.backend.apiresttransport.model.dto.DriverDto;
import com.company.transport.backend.apiresttransport.model.entity.Driver;
import com.company.transport.backend.apiresttransport.model.entity.Vehicle;
import com.company.transport.backend.apiresttransport.service.IDriver;

import jakarta.transaction.Transactional;

/**
 * Implementación del servicio para la gestión de conductores. Esta clase sigue
 * los principios SOLID para mantener una única responsabilidad y abstracción
 * adecuada.
 */
@Service
public class DriverImpl implements IDriver {
	private static final Logger log=LoggerFactory.getLogger(DriverImpl.class);
	@Autowired
	private DriverDao driverDao;
	@Autowired
	private VehicleDao vehicleDao;

	/**
	 * Guarda un nuevo conductor en la base de datos a partir de los datos
	 * proporcionados en un DriverDto.
	 *
	 * @param driverDto Los datos del conductor a ser guardados.
	 * @return El objeto Driver guardado en la base de datos.
	 */
	@Transactional
	@Override
	public Driver save(DriverDto driverDto) {
		// Convierte los datos del DriverDto en un objeto Driver siguiendo el principio
		// de abstracción adecuada.
		Driver driver = Driver.builder().id(driverDto.getId()).identificacion(driverDto.getIdentificacion())
				.nombre(driverDto.getNombre()).apellido(driverDto.getApellido()).telefono(driverDto.getTelefono())
				.direccion(driverDto.getDireccion()).vehicles(driverDto.getVehicles()).build();
		// Guarda el conductor en la base de datos utilizando el DriverDao, respetando
		// el principio de inversión de dependencia.
		return driverDao.save(driver);
	}

	/**
	 * Este método se encarga de obtener la lista de vehículos que no están
	 * asignados a un conductor, según la información proporcionada en un objeto
	 * DriverDto.
	 *
	 * @param driverDto El objeto DriverDto que contiene información sobre el
	 *                  conductor y los vehículos asignados.
	 * @return Una lista de vehículos que no están asignados al conductor.
	 */
	@Transactional
	@Override
	public List<Vehicle> getUnassignedVehicles(Driver driver) {
		// Obtiene todos los vehículos disponibles en la base de datos.
		List<Vehicle> allVehicles = vehicleDao.findAll();

		// Obtiene la lista de vehículos asignados al conductor desde el objeto
		// DriverDto.
		List<Vehicle> assignedVehicles = driver.getVehicles();
		System.out.println("Tamaño de allVehicles: " + allVehicles.size());
		System.out.println("Tamaño de assignedVehicles: " + assignedVehicles.size());
		// Filtra los vehículos y devuelve la lista de vehiculos no asignados al
		// conductor.
		return  allVehicles.stream().filter(vehicle -> !assignedVehicles.contains(vehicle)).collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public List<Vehicle> getAssignedVehicles(Integer driverId) {
		// Obtiene todos los vehículos disponibles en la base de datos.
		Driver driver = driverDao.findById(driverId).orElse(null);

		// Obtiene la lista de vehículos asignados al conductor desde el objeto
		// DriverDto.
		List<Vehicle> assignedVehicles = driver.getVehicles();
		// Filtra los vehículos y devuelve la lista de vehiculos no asignados al
		// conductor.
		return  assignedVehicles;
	}
	
	@Transactional
	@Override
	public Driver getDriverById(Integer driverId) {
		// Aquí implementa la lógica para buscar un conductor por su ID.
		// Puede utilizar el repositorio (DriverDao) o cualquier otro método de acceso a
		// datos.

		// Por ejemplo:
		Driver driver = driverDao.findById(driverId).orElse(null);

		if (driver != null) {
			return driver;
		}

		return null; // Devuelve null si no se encuentra el conductor.
	}

}

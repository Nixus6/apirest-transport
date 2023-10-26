package com.company.transport.backend.apiresttransport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.transport.backend.apiresttransport.model.dao.DriverDao;
import com.company.transport.backend.apiresttransport.model.dto.DriverDto;
import com.company.transport.backend.apiresttransport.model.entity.Driver;
import com.company.transport.backend.apiresttransport.service.IDriver;

import jakarta.transaction.Transactional;

@Service
public class DriverImpl implements IDriver{

	@Autowired
	private DriverDao driverDao;
	
	@Transactional
	@Override
	public Driver save(DriverDto driverDto) {
		// TODO Auto-generated method stub
		Driver driver = Driver.builder().id(driverDto.getId())
				.identificacion(driverDto.getIdentificacion())
				.nombre(driverDto.getNombre())
				.apellido(driverDto.getApellido())
				.telefono(driverDto.getTelefono())
				.direccion(driverDto.getDireccion())
				.build();
		return driverDao.save(driver);
	}

}

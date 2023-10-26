package com.company.transport.backend.apiresttransport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.company.transport.backend.apiresttransport.model.dto.DriverDto;
import com.company.transport.backend.apiresttransport.model.entity.Driver;
import com.company.transport.backend.apiresttransport.model.payload.MessageResponse;
import com.company.transport.backend.apiresttransport.service.IDriver;

@RestController
@RequestMapping("/api/v1")
public class DriverController {
	
	@Autowired
	private IDriver driverService;
	
	@PostMapping("driver")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody DriverDto driverDto) {
		Driver driverSave = null;
		try {
			driverSave = driverService.save(driverDto);		
			
			driverDto = DriverDto.builder()
					.id(driverSave.getId())
					.identificacion(driverSave.getIdentificacion())
					.nombre(driverSave.getNombre())
					.apellido(driverSave.getApellido())
					.telefono(driverSave.getTelefono())
					.direccion(driverSave.getDireccion())
					.build();
			
			return new ResponseEntity<>(MessageResponse.builder()
					.message("Guardado Correctamente")
					.object(driverDto)
					,HttpStatus.CREATED);
		} catch (DataAccessException e) {
			// TODO: handle exception
			return new ResponseEntity<>(MessageResponse.builder()
					.message(e.getMessage())
					.object(null)
					,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}

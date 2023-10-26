package com.company.transport.backend.apiresttransport.controller;

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

    private final IDriver driverService;

    public DriverController(IDriver driverService) {
        this.driverService = driverService;
    }

    /**
     * Maneja la creación de un nuevo conductor.
     *
     * @param driverDto Los datos del conductor a crear.
     * @return ResponseEntity con un mensaje de éxito y el conductor creado, o un mensaje de error.
     */
    @PostMapping("drivers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse<DriverDto>> create(@RequestBody DriverDto driverDto) {
        try {
            Driver driverSave = driverService.save(driverDto);
			DriverDto driverSaveDto = DriverDto.builder()
					.id(driverSave.getId())
					.identificacion(driverSave.getIdentificacion())
					.nombre(driverSave.getNombre())
					.apellido(driverSave.getApellido())
					.telefono(driverSave.getTelefono())
					.direccion(driverSave.getDireccion())
					.build();
            MessageResponse<DriverDto> response = MessageResponse.<DriverDto>builder()
                    .message("Guardado Correctamente")
                    .object(driverSaveDto)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } 
		/*
		 * catch (DriverAlreadyExistsException e) { // Maneja una excepción
		 * personalizada que ocurre si el conductor ya existe.
		 * MessageResponse<DriverDto> response = MessageResponse.<DriverDto>builder()
		 * .message(e.getMessage()) .object(null) .build(); return new
		 * ResponseEntity<>(response, HttpStatus.CONFLICT); }
		 */
        catch (DataAccessException e) {
            // Maneja una excepción de acceso a datos que puede ocurrir al interactuar con la base de datos.
            MessageResponse<DriverDto> response = MessageResponse.<DriverDto>builder()
                    .message(e.getMessage())
                    .object(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

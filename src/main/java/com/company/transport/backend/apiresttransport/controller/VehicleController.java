package com.company.transport.backend.apiresttransport.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.transport.backend.apiresttransport.model.dto.VehicleDto;
import com.company.transport.backend.apiresttransport.model.entity.Vehicle;
import com.company.transport.backend.apiresttransport.model.payload.MessageResponse;
import com.company.transport.backend.apiresttransport.service.IVehicle;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {
	private final IVehicle vehicleService;
	
	// Constructor que inyecta una implementación de IVehicle para seguir el principio de inversión de dependencia (DIP).
    public VehicleController(IVehicle vehicleService) {
        this.vehicleService = vehicleService;
    }
    
    /**
     * Maneja la creación de un nuevo vehículo.
     *
     * @param vehicleDto Los datos del vehículo a crear.
     * @return ResponseEntity con un mensaje de éxito y el vehículo creado, o un mensaje de error.
     */
    @PostMapping("vehicles")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse<VehicleDto>> create(@RequestBody VehicleDto vehicleDto) {
        try {
        	System.out.println("vehicleDto "+ vehicleDto);
        	// Guarda el vehículo utilizando el servicio IVehicle.
        	Vehicle vehicleSave = vehicleService.save(vehicleDto);
        	// Convierte el vehículo guardado en un DTO para la respuesta.
			VehicleDto vehicleSaveDto = VehicleDto.builder()
					.id(vehicleSave.getId())
					.placa(vehicleSave.getPlaca())
					.modelo(vehicleSave.getModelo())
					.capacidad(vehicleSave.getCapacidad())
					.build();
			// Prepara una respuesta exitosa.
            MessageResponse<VehicleDto> response = MessageResponse.<VehicleDto>builder()
                    .message("Guardado Correctamente")
                    .object(vehicleSaveDto)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } 
        catch (DataAccessException e) {
            // Maneja una excepción de acceso a datos que puede ocurrir al interactuar con la base de datos.
            MessageResponse<VehicleDto> response = MessageResponse.<VehicleDto>builder()
                    .message(e.getMessage())
                    .object(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PostMapping("vehicle-to-driver")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse<VehicleDto>> assignVehicleToDriver(
            @RequestParam Integer vehicleId,
            @RequestParam Integer driverId) {
    	Vehicle vehicleSave = vehicleService.assignVehicleToDriver(vehicleId, driverId);
		VehicleDto vehicleSaveDto = VehicleDto.builder()
				.id(vehicleSave.getId())
				.placa(vehicleSave.getPlaca())
				.modelo(vehicleSave.getModelo())
				.capacidad(vehicleSave.getCapacidad())
				.build();
		// Prepara una respuesta exitosa.
        MessageResponse<VehicleDto> response = MessageResponse.<VehicleDto>builder()
                .message("Guardado Correctamente")
                .object(vehicleSaveDto)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

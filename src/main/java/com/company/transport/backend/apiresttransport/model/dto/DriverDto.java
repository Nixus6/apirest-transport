package com.company.transport.backend.apiresttransport.model.dto;

import java.io.Serializable;
import java.util.List;

import com.company.transport.backend.apiresttransport.model.entity.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Clase de transferencia de datos (DTO) que representa los datos de un conductor.
 * Este DTO se utiliza para transferir datos entre la capa de presentación y la capa de servicio.
 * Esta clase sigue los principios SOLID al mantener una única responsabilidad y ser una simple abstracción de datos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DriverDto implements Serializable {

	private Integer id;
	private String identificacion;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
	private List<Vehicle> vehicles;
	
}

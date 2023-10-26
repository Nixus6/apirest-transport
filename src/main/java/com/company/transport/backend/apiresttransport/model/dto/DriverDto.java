package com.company.transport.backend.apiresttransport.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

}

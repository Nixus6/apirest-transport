package com.company.transport.backend.apiresttransport.model.dto;

import com.company.transport.backend.apiresttransport.model.entity.Driver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Data Transfer Object (DTO) para representar información de vehículos.
 * Este DTO se utiliza para transferir datos entre la capa de presentación y la capa de servicio.
 * Sigue el principio de segregación de interfaces (ISP) al proporcionar solo los campos necesarios.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class VehicleDto {
	
    /**
     * Identificador del vehículo.
     */
	private Integer id;
    /**
     * Placa del vehículo.
     */
	private String placa;
    /**
     * Modelo del vehículo.
     */
	private String modelo;
    /**
     * Capacidad del vehículo.
     */
	private String capacidad;
    /**
     * Conductor asociado al vehículo.
     * La relación con Driver sigue el principio de dependencia inversa (DIP).
     */
	private Driver driver;
}

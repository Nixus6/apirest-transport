package com.company.transport.backend.apiresttransport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.transport.backend.apiresttransport.model.dao.DriverDao;
import com.company.transport.backend.apiresttransport.model.dao.VehicleDao;
import com.company.transport.backend.apiresttransport.model.dto.VehicleDto;
import com.company.transport.backend.apiresttransport.model.entity.Driver;
import com.company.transport.backend.apiresttransport.model.entity.Vehicle;
import com.company.transport.backend.apiresttransport.service.IVehicle;

import jakarta.transaction.Transactional;

/**
 * Implementación del servicio para la gestión de vehículos.
 * Esta clase sigue los principios SOLID para mantener una única responsabilidad y abstracción adecuada.
 */
@Service
public class VehicleImpl implements IVehicle {

    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private DriverDao driverDao;
    
    /**
     * Guarda un nuevo vehículo en la base de datos a partir de los datos proporcionados en un VehicleDto.
     *
     * @param vehicleDto Los datos del vehículo a ser guardados.
     * @return El objeto Vehicle guardado en la base de datos.
     */
    @Transactional
    @Override
    public Vehicle save(VehicleDto vehicleDto) {
        // Convierte los datos del VehicleDto en un objeto Vehicle siguiendo el principio de abstracción adecuada.
        Vehicle vehicle = Vehicle.builder()
                .id(vehicleDto.getId())
                .placa(vehicleDto.getPlaca())
                .modelo(vehicleDto.getModelo())
                .capacidad(vehicleDto.getCapacidad())
                .build();

        // Guarda el vehículo en la base de datos utilizando el VehicleDao, respetando el principio de inversión de dependencia.
        return vehicleDao.save(vehicle);
    }
    
    @Transactional
    @Override
    public Vehicle assignVehicleToDriver(Integer vehicleId, Integer driverId) {
        Vehicle vehicle = vehicleDao.findById(vehicleId).orElse(null);
        Driver driver = driverDao.findById(driverId).orElse(null);
        
        System.out.print(driver);
        if (vehicle != null && driver != null) {
            vehicle.setDriver(driver);
            vehicleDao.save(vehicle);
        }
        return vehicle;
    }

}

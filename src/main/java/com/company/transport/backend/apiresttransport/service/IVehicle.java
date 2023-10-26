package com.company.transport.backend.apiresttransport.service;

import com.company.transport.backend.apiresttransport.model.dto.VehicleDto;
import com.company.transport.backend.apiresttransport.model.entity.Vehicle;

public interface IVehicle {
	
	Vehicle save(VehicleDto vehicle);
	Vehicle assignVehicleToDriver(Integer vehicleId, Integer driverId);

}

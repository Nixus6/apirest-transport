package com.company.transport.backend.apiresttransport.service;
import java.util.List;
import java.util.Set;

import com.company.transport.backend.apiresttransport.model.dto.DriverDto;
import com.company.transport.backend.apiresttransport.model.entity.Driver;
import com.company.transport.backend.apiresttransport.model.entity.Vehicle;

public interface IDriver {
	
	Driver save(DriverDto driver);	
	List<Vehicle> getUnassignedVehicles(DriverDto driver);
	DriverDto getDriverById(Integer driverId);
}

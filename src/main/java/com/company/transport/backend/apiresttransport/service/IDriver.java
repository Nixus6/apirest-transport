package com.company.transport.backend.apiresttransport.service;

import com.company.transport.backend.apiresttransport.model.dto.DriverDto;
import com.company.transport.backend.apiresttransport.model.entity.Driver;

public interface IDriver {
	
	Driver save(DriverDto driver);	
	
}

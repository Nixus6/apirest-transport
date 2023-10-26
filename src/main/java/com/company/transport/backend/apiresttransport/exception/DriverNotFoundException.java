package com.company.transport.backend.apiresttransport.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DriverNotFoundException extends RuntimeException {
    private Integer driverId;

    public DriverNotFoundException(Integer driverId) {
        super("Driver not found with ID: " + driverId);
        this.driverId = driverId;
    }
}

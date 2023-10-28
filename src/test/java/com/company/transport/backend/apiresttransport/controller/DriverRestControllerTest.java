package com.company.transport.backend.apiresttransport.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.company.transport.backend.apiresttransport.model.dto.DriverDto;
import com.company.transport.backend.apiresttransport.model.entity.Driver;
import com.company.transport.backend.apiresttransport.model.entity.Vehicle;
import com.company.transport.backend.apiresttransport.model.payload.MessageResponse;
import com.company.transport.backend.apiresttransport.service.IDriver;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DriverRestControllerTest {

    @InjectMocks
    private DriverController driverController;

    @Mock
    private IDriver driverService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test 1")
    public void testGetUnassignedVehicles() {
        // Arrange
        Integer driverId = 1;
        Driver mockDriverDto = new Driver();
        mockDriverDto.setId(driverId);

        // Mock the behavior of driverService
        Mockito.when(driverService.getDriverById(driverId)).thenReturn(mockDriverDto);

        List<Vehicle> mockUnassignedVehicles = Collections.singletonList(new Vehicle());

        Mockito.when(driverService.getUnassignedVehicles(mockDriverDto)).thenReturn(mockUnassignedVehicles);

        // Act
        ResponseEntity<MessageResponse<List<Vehicle>>> responseEntity = driverController.getUnassignedVehicles(driverId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        MessageResponse<List<Vehicle>> response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals("", response.getMessage());
        assertEquals(mockUnassignedVehicles, response.getObject());
    }

    @Test
    @DisplayName("Test 2")
    public void testGetUnassignedVehiclesDriverNotFound() {
        // Arrange
        Integer driverId = 1;

        // Mock the behavior of driverService when driver is not found
        Mockito.when(driverService.getDriverById(driverId)).thenReturn(null);

        // Act
        ResponseEntity<MessageResponse<List<Vehicle>>> responseEntity = driverController.getUnassignedVehicles(driverId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Test 3")
    public void testGetUnassignedVehiclesDataAccessException() {
        // Arrange
        Integer driverId = 1;
        Driver mockDriverDto = new Driver();
        mockDriverDto.setId(driverId);

        // Mock the behavior of driverService to throw a DataAccessException
        Mockito.when(driverService.getDriverById(driverId)).thenReturn(mockDriverDto);

        // Act
        ResponseEntity<MessageResponse<List<Vehicle>>> responseEntity = driverController.getUnassignedVehicles(driverId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        MessageResponse<List<Vehicle>> response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals("Mock Exception", response.getMessage());
        assertEquals(null, response.getObject());
    }
}

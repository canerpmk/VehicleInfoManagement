package com.example.sahibinden.controller;

import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.dto.CarRequest;
import com.example.sahibinden.model.dto.CarResponse;
import com.example.sahibinden.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @Test
    void testGetAllCars() {
        // Mock CarResponse list
        List<CarResponse> mockCarResponses = new ArrayList<>();
        when(carService.getAllCars()).thenReturn(new ArrayList<>());

        // Call the method
        ResponseEntity<List<CarResponse>> responseEntity = carController.getAllCars();

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockCarResponses, responseEntity.getBody());

        verify(carService, times(1)).getAllCars();
    }

    @Test
    void testGetCarById() {
        Long carId = 1L;
        Car mockCar = new Car();
        when(carService.getCarById(carId)).thenReturn(mockCar);

        // Call the method
        ResponseEntity<CarResponse> responseEntity = carController.getCarById(carId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(carService, times(1)).getCarById(carId);
    }

    @Test
    void testCreateCar() {
        CarRequest mockCarRequest = new CarRequest();
        Car mockAddedCar = new Car();
        when(carService.addCar(any(Car.class))).thenReturn(mockAddedCar);

        // Call the method
        ResponseEntity<CarResponse> responseEntity = carController.createCar(mockCarRequest);

        // Verify
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(carService, times(1)).addCar(any(Car.class));
    }

    @Test
    void testUpdateCar() {
        Long carId = 1L;
        CarRequest mockCarRequest = new CarRequest();
        Car mockUpdatedCar = new Car();
        when(carService.updateCar(any(Car.class))).thenReturn(mockUpdatedCar);

        // Call the method
        ResponseEntity<CarResponse> responseEntity = carController.updateMarka(carId, mockCarRequest);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(carService, times(1)).updateCar(any(Car.class));
    }

    @Test
    void testDeleteCar() {
        Long carId = 1L;
        doNothing().when(carService).deleteCarById(carId);

        // Call the method
        ResponseEntity<Void> responseEntity = carController.deleteCar(carId);

        // Verify
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        verify(carService, times(1)).deleteCarById(carId);
    }
}

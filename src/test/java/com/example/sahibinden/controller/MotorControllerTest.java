package com.example.sahibinden.controller;

import com.example.sahibinden.model.Motor;
import com.example.sahibinden.model.dto.MotorRequest;
import com.example.sahibinden.model.dto.MotorResponse;
import com.example.sahibinden.service.ModelService;
import com.example.sahibinden.service.MotorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MotorControllerTest {
    @Mock
    private MotorService motorService;

    @InjectMocks
    private MotorController motorController;
    @Test
    void getMotorById() {
        Long motorId = 1L;
        Motor mockMotor = new Motor();
        when(motorService.getMotorById(motorId)).thenReturn(mockMotor);

        ResponseEntity<MotorResponse> responseEntity = motorController.getMotorById(motorId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(motorService, times(1)).getMotorById(motorId);
    }
    @Test
    void getAllMotorlar() {
        List<Motor> mockMotorlar = Arrays.asList(new Motor(), new Motor());
        when(motorService.getAllMotor()).thenReturn(mockMotorlar);

        ResponseEntity<List<MotorResponse>> responseEntity = motorController.getAllMotorlar();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockMotorlar.size(), responseEntity.getBody().size());

        verify(motorService, times(1)).getAllMotor();
    }
    @Test
    void addMotor() {
        MotorRequest mockMotorRequest = new MotorRequest();
        Motor mockAddedMotor = new Motor();
        when(motorService.addMotor(any(Motor.class))).thenReturn(mockAddedMotor);

        ResponseEntity<MotorResponse> responseEntity = motorController.addMotor(mockMotorRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(motorService, times(1)).addMotor(any(Motor.class));
    }
    @Test
    void updateMotor() {
        Long motorId = 1L;
        MotorRequest mockMotorRequest = new MotorRequest();
        Motor mockUpdatedMotor = new Motor();
        when(motorService.updateMotor(any(Motor.class))).thenReturn(mockUpdatedMotor);

        ResponseEntity<MotorResponse> responseEntity = motorController.updateMotor(motorId, mockMotorRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(motorService, times(1)).updateMotor(any(Motor.class));
    }
    @Test
    void deleteMotor() {
        Long motorId = 1L;

        ResponseEntity<Void> responseEntity = motorController.deleteMotor(motorId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        verify(motorService, times(1)).deleteMotorById(motorId);
    }





}

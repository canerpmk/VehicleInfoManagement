package com.example.sahibinden.controller;

import com.example.sahibinden.model.Motor;
import com.example.sahibinden.model.dto.MotorRequest;
import com.example.sahibinden.model.dto.MotorResponse;
import com.example.sahibinden.service.MotorService;
import com.example.sahibinden.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

        Motor mockMotor = TestUtils.motorBuilder();
        when(motorService.getMotorById(mockMotor.getId())).thenReturn(mockMotor);


        ResponseEntity<MotorResponse> responseEntity = motorController.getMotorById(mockMotor.getId());


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(motorService, times(1)).getMotorById(mockMotor.getId());
    }

    @Test
    void getAllMotorlar() {
        List<Motor> mockMotorlar = List.of(TestUtils.motorBuilder(), TestUtils.motorBuilder());
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
        Motor mockAddedMotor = TestUtils.motorBuilder();
        when(motorService.addMotor(any(Motor.class))).thenReturn(mockAddedMotor);


        ResponseEntity<MotorResponse> responseEntity = motorController.addMotor(mockMotorRequest);


        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(motorService, times(1)).addMotor(any(Motor.class));
    }

    @Test
    void updateMotor() {

        MotorRequest mockMotorRequest = new MotorRequest();
        Motor mockUpdatedMotor = TestUtils.motorBuilder();
        when(motorService.updateMotor(any(Motor.class))).thenReturn(mockUpdatedMotor);

        ResponseEntity<MotorResponse> responseEntity = motorController.updateMotor(mockUpdatedMotor.getId(), mockMotorRequest);

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

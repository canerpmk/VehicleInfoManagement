package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Motor;
import com.example.sahibinden.model.entity.MotorEntity;
import com.example.sahibinden.repository.MotorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MotorServiceTest {
    @Mock
    private MotorRepository motorRepository;

    @InjectMocks
    private MotorServiceImpl motorService;

    @Test
    void testGetMotorById() {
        // Mocked motorEntity and motor
        MotorEntity motorEntity = new MotorEntity();
        motorEntity.setId(1L);
        // Set other properties for motorEntity

        when(motorRepository.findById(1L)).thenReturn(Optional.of(motorEntity));

        // Call the method
        Motor returnedMotor = motorService.getMotorById(1L);

        // Verification
        assertNotNull(returnedMotor);
        assertEquals(motorEntity.getId(), returnedMotor.getId());
        // Verify interactions
        verify(motorRepository).findById(1L);
        verifyNoMoreInteractions(motorRepository);
    }

    @Test
    void testGetAllMotor() {
        // Mocked motorEntities
        List<MotorEntity> motorEntities = new ArrayList<>();
        // Create and add MotorEntity instances to motorEntities
        // ...

        when(motorRepository.findAll()).thenReturn(motorEntities);

        // Call the method
        List<Motor> returnedMotors = motorService.getAllMotor();

        // Verification
        assertNotNull(returnedMotors);
        assertEquals(motorEntities.size(), returnedMotors.size());
        // Verify interactions
        verify(motorRepository).findAll();
        verifyNoMoreInteractions(motorRepository);
    }

    @Test
    void testGetKMotorByShortName() {
        // Mocked motorEntity and motor
        MotorEntity motorEntity = new MotorEntity();
        motorEntity.setShortName("ABC");
        // Set other properties for motorEntity

        when(motorRepository.findMotorEntityByShortName("ABC")).thenReturn(Optional.of(motorEntity));

        // Call the method
        Motor returnedMotor = motorService.getKMotorByShortName("ABC");

        // Verification
        assertNotNull(returnedMotor);
        assertEquals(motorEntity.getShortName(), returnedMotor.getShortName());
        // Verify interactions
        verify(motorRepository).findMotorEntityByShortName("ABC");
        verifyNoMoreInteractions(motorRepository);
    }
    @Test
    void testAddMotor() {
        // Mocked input motor and motorEntity
        Motor inputMotor = new Motor();
        // Set properties for inputMotor

        MotorEntity mockedMotorEntity = new MotorEntity();
        // Set properties for mockedMotorEntity
        when(motorRepository.save(any())).thenReturn(mockedMotorEntity);

        // Call the method
        Motor returnedMotor = motorService.addMotor(inputMotor);

        // Verification
        assertNotNull(returnedMotor);
        assertEquals(mockedMotorEntity.getId(), returnedMotor.getId());
        // Verify interactions
        verify(motorRepository).save(any());
        verifyNoMoreInteractions(motorRepository);
    }

    @Test
    void testAddMotors() {
        // Mocked input motor list and motorEntity list
        List<Motor> inputMotors = new ArrayList<>();
        // Create and add Motor instances to inputMotors
        // ...

        List<MotorEntity> mockedMotorEntities = new ArrayList<>();
        // Create and add MotorEntity instances to mockedMotorEntities
        // ...

        when(motorRepository.saveAll(anyIterable())).thenReturn(mockedMotorEntities);

        // Call the method
        List<Motor> returnedMotors = motorService.addMotors(inputMotors);

        // Verification
        assertNotNull(returnedMotors);
        assertEquals(mockedMotorEntities.size(), returnedMotors.size());
        // Verify interactions
        verify(motorRepository).saveAll(anyIterable());
        verifyNoMoreInteractions(motorRepository);
    }

    @Test
    void testUpdateMotor() {
        // Mocked input motor and existing motorEntity
        Motor inputMotor = new Motor();
        // Set properties for inputMotor

        MotorEntity existingMotorEntity = new MotorEntity();
        // Set properties for existingMotorEntity
        when(motorRepository.existsById(inputMotor.getId())).thenReturn(true);
        when(motorRepository.save(any())).thenReturn(existingMotorEntity);

        // Call the method
        Motor updatedMotor = motorService.updateMotor(inputMotor);

        // Verification
        assertNotNull(updatedMotor);
        assertEquals(existingMotorEntity.getId(), updatedMotor.getId());
        // Verify interactions
        verify(motorRepository).existsById(inputMotor.getId());
        verify(motorRepository).save(any());
        verifyNoMoreInteractions(motorRepository);
    }

    @Test
    void testDeleteMotorById() {
        // Mocked motor ID
        Long motorId = 1L;
        when(motorRepository.existsById(motorId)).thenReturn(false);

        // Call the method
        boolean isDeleted = motorService.deleteMotorById(motorId);

        // Verification
        assertTrue(isDeleted);
        // Verify interactions
        verify(motorRepository).deleteById(motorId);
        verify(motorRepository).existsById(motorId);
        verifyNoMoreInteractions(motorRepository);
    }

}

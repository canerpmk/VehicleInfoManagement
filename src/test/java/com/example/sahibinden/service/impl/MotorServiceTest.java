package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Motor;
import com.example.sahibinden.model.entity.MotorEntity;
import com.example.sahibinden.repository.MotorRepository;
import com.example.sahibinden.utils.TestUtils;
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
        MotorEntity motorEntity = TestUtils.motorEntity();

        when(motorRepository.findById(motorEntity.getId())).thenReturn(Optional.of(motorEntity));

        Motor returnedMotor = motorService.getMotorById(motorEntity.getId());

        assertNotNull(returnedMotor);
        assertEquals(motorEntity.getId(), returnedMotor.getId());

        verify(motorRepository).findById(motorEntity.getId());
        verifyNoMoreInteractions(motorRepository);
    }

    @Test
    void testGetAllMotor() {
        List<MotorEntity> motorEntities = new ArrayList<>();

        when(motorRepository.findAll()).thenReturn(motorEntities);

        List<Motor> returnedMotors = motorService.getAllMotor();

        assertNotNull(returnedMotors);
        assertEquals(motorEntities.size(), returnedMotors.size());

        verify(motorRepository).findAll();
        verifyNoMoreInteractions(motorRepository);
    }

    @Test
    void testGetKMotorByShortName() {

        MotorEntity motorEntity = TestUtils.motorEntity(TestUtils.motorEntity().getId(), "x");


        when(motorRepository.findMotorEntityByShortName("x")).thenReturn(Optional.of(motorEntity));

        Motor returnedMotor = motorService.getKMotorByShortName("x");

        assertNotNull(returnedMotor);
        assertEquals(motorEntity.getShortName(), returnedMotor.getShortName());

        verify(motorRepository).findMotorEntityByShortName("x");
        verifyNoMoreInteractions(motorRepository);
    }

    @Test
    void testAddMotor() {

        Motor inputMotor = TestUtils.motorBuilder();


        MotorEntity mockedMotorEntity = TestUtils.motorEntity();

        when(motorRepository.save(any())).thenReturn(mockedMotorEntity);


        Motor returnedMotor = motorService.addMotor(inputMotor);


        assertNotNull(returnedMotor);
        assertEquals(mockedMotorEntity.getId(), returnedMotor.getId());

        verify(motorRepository).save(any());
        verifyNoMoreInteractions(motorRepository);
    }

    @Test
    void testAddMotors() {

        List<Motor> inputMotors = new ArrayList<>();


        List<MotorEntity> mockedMotorEntities = List.of(TestUtils.motorEntity());


        when(motorRepository.saveAll(anyIterable())).thenReturn(mockedMotorEntities);


        List<Motor> returnedMotors = motorService.addMotors(inputMotors);


        assertNotNull(returnedMotors);
        assertEquals(mockedMotorEntities.size(), returnedMotors.size());

        verify(motorRepository).saveAll(anyIterable());
        verifyNoMoreInteractions(motorRepository);
    }

    @Test
    void testUpdateMotor() {
        Motor inputMotor = TestUtils.motorBuilder();

        MotorEntity existingMotorEntity = TestUtils.motorEntity();

        when(motorRepository.existsById(inputMotor.getId())).thenReturn(true);
        when(motorRepository.save(any())).thenReturn(existingMotorEntity);


        Motor updatedMotor = motorService.updateMotor(inputMotor);


        assertNotNull(updatedMotor);
        assertEquals(existingMotorEntity.getId(), updatedMotor.getId());

        verify(motorRepository).existsById(inputMotor.getId());
        verify(motorRepository).save(any());
        verifyNoMoreInteractions(motorRepository);
    }

    @Test
    void testDeleteMotorById() {

        Long motorId = TestUtils.randomId();
        when(motorRepository.existsById(motorId)).thenReturn(false);


        boolean isDeleted = motorService.deleteMotorById(motorId);


        assertTrue(isDeleted);

        verify(motorRepository).deleteById(motorId);
        verify(motorRepository).existsById(motorId);
        verifyNoMoreInteractions(motorRepository);
    }

}

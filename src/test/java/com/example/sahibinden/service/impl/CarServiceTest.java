package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.entity.CarEntity;
import com.example.sahibinden.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private MarkaRepository markaService;
    @Mock
    private ModelRepository modelService;
    @Mock
    private MotorRepository motorService;
    @Mock
    private OzellikRepository ozellikService;
    @Mock
    private PaketRepository paketService;
    @Mock
    private KasaRepository kasaService;
    @InjectMocks
    CarServiceImpl service;

    @Test
    void getCarById() {
        //Given
        final long carId = 3L;
        CarEntity carEntity = CarEntity.builder().id(carId).build();
        Optional<CarEntity> optionalCarEntity = Optional.of(carEntity);
        Car expected = Car.builder().id(carEntity.getId()).build();

        Mockito.when(carRepository.findById(carId)).thenReturn(optionalCarEntity);

        //When
        Car actual = service.getCarById(carId);

        //Then
        Assertions.assertEquals(expected, actual);


    }

    @Test
    void getAllCars() {
    }

    @Test
    void addCar() {
    }

    @Test
    void updateCar() {
    }

    @Test
    void deleteCarById() {
    }
}
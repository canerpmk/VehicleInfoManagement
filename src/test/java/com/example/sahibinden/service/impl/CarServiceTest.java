package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.*;
import com.example.sahibinden.model.entity.*;
import com.example.sahibinden.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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
    CarServiceImpl carService;

    @Test
    void getCarById() {
        //Given
        final long carId = 3L;
        CarEntity carEntity = CarEntity.builder().id(carId).build();
        Optional<CarEntity> optionalCarEntity = Optional.of(carEntity);
        Car expected = Car.builder().id(carEntity.getId()).build();

        when(carRepository.findById(carId)).thenReturn(optionalCarEntity);

        //When
        Car actual = carService.getCarById(carId);

        //Then
        assertEquals(expected, actual);


    }

    @Test
    void getAllCars() {
        // Given
        CarEntity carEntity1 = CarEntity.builder().id(1L).build();
        CarEntity carEntity2 = CarEntity.builder().id(2L).build();
        List<CarEntity> carEntities = Arrays.asList(carEntity1, carEntity2);

        when(carRepository.findAll()).thenReturn(carEntities);

        // When
        List<Car> cars = carService.getAllCars();

        // Then
        assertEquals(2, cars.size()); // Assuming two cars were returned
        assertEquals(carEntity1.getId(), cars.get(0).getId());
        assertEquals(carEntity2.getId(), cars.get(1).getId());
        // Additional assertions or comparisons based on your implementation
    }

    @Test
    void addCar() {
        // Given
        Car car = Car.builder()
                .marka(Marka.builder().id(1L).build())
                .model(Model.builder().id(2L).build())
                .motor(Motor.builder().id(3L).build())
                .ozellik(Ozellik.builder().id(4L).build())
                .paket(Paket.builder().id(5L).build())
                .kasa(Kasa.builder().id(6L).build())
                .build();

        MarkaEntity mockMarkaEntity = MarkaEntity.builder().id(1L).build();
        ModelEntity mockModelEntity = ModelEntity.builder().id(2L).build();
        MotorEntity mockMotorEntity = MotorEntity.builder().id(3L).build();
        OzellikEntity mockOzellikEntity = OzellikEntity.builder().id(4L).build();
        PaketEntity mockPaketEntity = PaketEntity.builder().id(5L).build();
        KasaEntity mockKasaEntity = KasaEntity.builder().id(6L).build();

        when(markaService.findById(1L)).thenReturn(Optional.of(mockMarkaEntity));
        when(modelService.findById(2L)).thenReturn(Optional.of(mockModelEntity));
        when(motorService.findById(3L)).thenReturn(Optional.of(mockMotorEntity));
        when(ozellikService.findById(4L)).thenReturn(Optional.of(mockOzellikEntity));
        when(paketService.findById(5L)).thenReturn(Optional.of(mockPaketEntity));
        when(kasaService.findById(6L)).thenReturn(Optional.of(mockKasaEntity));

        CarEntity mockAddedCarEntity = CarEntity.builder().id(7L).build();
        when(carRepository.save(Mockito.any(CarEntity.class))).thenReturn(mockAddedCarEntity);

        // When
        Car addedCar = carService.addCar(car);

        // Then
        Assertions.assertNotNull(addedCar);
        assertEquals(mockAddedCarEntity.getId(), addedCar.getId());
    }

    @Test
    void updateCar() {
        // Given
        final long carId = 3L;
        CarEntity carEntity = CarEntity.builder().id(carId).build();
        Car updatedCar = Car.builder().id(carEntity.getId()).name("Updated Car").build();

        when(carRepository.existsById(carId)).thenReturn(true);
        when(carRepository.save(any(CarEntity.class))).thenReturn(CarEntity.fromModel(updatedCar));

        // When
        Car actualUpdatedCar = carService.updateCar(updatedCar);

        // Then
        assertEquals(updatedCar.getId(), actualUpdatedCar.getId());
        assertEquals(updatedCar.getName(), actualUpdatedCar.getName());
        assertEquals(updatedCar.getOzellik(), actualUpdatedCar.getOzellik());
    }

    @Test
    void deleteCarById() {
        Long carId = 1L;
        doNothing().when(carRepository).deleteById(carId);

        when(carRepository.existsById(carId)).thenReturn(false);

        boolean isDeleted = carService.deleteCarById(carId);

        assertTrue(isDeleted);
        verify(carRepository, times(1)).deleteById(carId);
        verify(carRepository, times(1)).existsById(carId);
    }
}

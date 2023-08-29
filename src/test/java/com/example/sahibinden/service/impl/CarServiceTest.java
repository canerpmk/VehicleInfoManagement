package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.entity.*;
import com.example.sahibinden.repository.*;
import com.example.sahibinden.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

        final long carId = 3L;
        CarEntity carEntity = TestUtils.carEntity();
        Optional<CarEntity> optionalCarEntity = Optional.of(carEntity);
        Car expected = TestUtils.carBuilder(carEntity.getId());

        when(carRepository.findById(carId)).thenReturn(optionalCarEntity);


        Car actual = carService.getCarById(carId);


        assertEquals(expected, actual);


    }

    @Test
    void getAllCars() {
        List<CarEntity> carEntityList = List.of(TestUtils.carEntity(), TestUtils.carEntity());


        when(carRepository.findAll()).thenReturn(carEntityList);


        List<Car> cars = carService.getAllCars();


        assertEquals(carEntityList.size(), cars.size());
        assertEquals(carEntityList.get(0).getId(), cars.get(0).getId());
        assertEquals(carEntityList.get(1).getId(), cars.get(1).getId());

    }

    @Test
    void addCar() {
        Car car = TestUtils.car();

        MarkaEntity mockMarkaEntity = TestUtils.markaEntity();
        ModelEntity mockModelEntity = TestUtils.modelEntity();
        MotorEntity mockMotorEntity = TestUtils.motorEntity();
        OzellikEntity mockOzellikEntity = TestUtils.ozellikEntity();
        PaketEntity mockPaketEntity = TestUtils.paketEntity();
        KasaEntity mockKasaEntity = TestUtils.kasaEntity();

        when(markaService.findById(car.getMarka().getId())).thenReturn(Optional.of(mockMarkaEntity));
        when(modelService.findById(car.getModel().getId())).thenReturn(Optional.of(mockModelEntity));
        when(motorService.findById(car.getMotor().getId())).thenReturn(Optional.of(mockMotorEntity));
        when(ozellikService.findById(car.getOzellik().getId())).thenReturn(Optional.of(mockOzellikEntity));
        when(paketService.findById(car.getPaket().getId())).thenReturn(Optional.of(mockPaketEntity));
        when(kasaService.findById(car.getKasa().getId())).thenReturn(Optional.of(mockKasaEntity));

        CarEntity mockAddedCarEntity = CarEntity.builder().id(7L).build();
        when(carRepository.save(Mockito.any(CarEntity.class))).thenReturn(mockAddedCarEntity);


        Car addedCar = carService.addCar(car);


        Assertions.assertNotNull(addedCar);
        assertEquals(mockAddedCarEntity.getId(), addedCar.getId());
    }

    @Test
    void updateCar() {
        CarEntity carEntity = TestUtils.carEntity(TestUtils.carEntity().getId(), "update car");
        Car updatedCar = TestUtils.car(carEntity.getId(), carEntity.getName());


        when(carRepository.existsById(carEntity.getId())).thenReturn(true);
        when(carRepository.save(any(CarEntity.class))).thenReturn(CarEntity.fromModel(updatedCar));

        Car actualUpdatedCar = carService.updateCar(updatedCar);

        assertEquals(updatedCar.getId(), actualUpdatedCar.getId());
        assertEquals(updatedCar.getName(), actualUpdatedCar.getName());
        assertEquals(updatedCar.getOzellik(), actualUpdatedCar.getOzellik());
    }

    @Test
    void deleteCarById() {
        Long carId = TestUtils.randomId();
        doNothing().when(carRepository).deleteById(carId);

        when(carRepository.existsById(carId)).thenReturn(false);

        boolean isDeleted = carService.deleteCarById(carId);

        assertTrue(isDeleted);
        verify(carRepository, times(1)).deleteById(carId);
        verify(carRepository, times(1)).existsById(carId);
    }
}

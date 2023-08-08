package com.example.sahibinden.service.impl;

import com.example.sahibinden.exception.model.NotFoundException;
import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.Paket;
import com.example.sahibinden.model.entity.*;
import com.example.sahibinden.repository.*;
import com.example.sahibinden.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final MarkaRepository markaService;
    private final ModelRepository modelService;
    private final MotorRepository motorService;
    private final PaketRepository paketService;
    private final OzellikRepository ozellikService;

    public Car getCarById(Long id) {
        CarEntity carEntity = carRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return CarEntity.fromModel(carEntity);
    }

    public List<Car> getAllCars() {
        List<CarEntity> carEntities = carRepository.findAll();
        return carEntities.stream()
                .map(Car::fromEntity)
                .collect(Collectors.toList());
    }
    public Car addCar(Car car) {
        MarkaEntity marka=markaService.findById(car.getMarka().getId()).get();
        ModelEntity model=modelService.findById(car.getModel().getId()).get();
        MotorEntity motor=motorService.findById(car.getMotor().getId()).get();
        CarEntity carEntity = CarEntity.fromModel(car);
        carEntity.setMarka(marka);
        carEntity.setModel(model);
        carEntity.setMotor(motor);


        CarEntity addedCarEntity = carRepository.save(carEntity);
        return Car.fromEntity(addedCarEntity);
    }



}
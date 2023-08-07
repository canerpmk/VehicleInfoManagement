package com.example.sahibinden.service.impl;

import com.example.sahibinden.exception.model.NotFoundException;
import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.dto.CarRequest;
import com.example.sahibinden.model.dto.CarResponse;
import com.example.sahibinden.model.entity.CarEntity;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.repository.CarRepository;
import com.example.sahibinden.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

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
    public Car addCar(Car car) {//Car service de car olması lazım
        CarEntity carEntity = CarEntity.fromModel(car);
        CarEntity addedCarEntity = carRepository.save(carEntity);
        return Car.fromEntity(addedCarEntity);
    }



}
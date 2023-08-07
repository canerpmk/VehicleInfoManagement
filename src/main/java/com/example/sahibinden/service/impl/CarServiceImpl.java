package com.example.sahibinden.service.impl;

import com.example.sahibinden.exception.model.NotFoundException;
import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.entity.CarEntity;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.repository.CarRepository;
import com.example.sahibinden.repository.MarkaRepository;
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
        MarkaEntity marka=markaService.findById(car.getMarka().getId()).get();
        CarEntity carEntity = CarEntity.fromModel(car);
        carEntity.setMarka(marka);

        CarEntity addedCarEntity = carRepository.save(carEntity);
        return Car.fromEntity(addedCarEntity);
    }



}
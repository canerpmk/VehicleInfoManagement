package com.example.sahibinden.service;

import com.example.sahibinden.model.Car;

import java.util.List;


public interface CarService {
    List<Car> getAllCars();

    Car getCarById(Long id);

    Car addCar(Car car);

    Car updateCar(Car car);

    boolean deleteCarById(Long id);


}
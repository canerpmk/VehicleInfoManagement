package com.example.sahibinden.service;
import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.dto.CarResponse;
import com.example.sahibinden.model.entity.CarEntity;
import org.springframework.http.ResponseEntity;


import java.util.List;


public interface CarService {
    List<Car> getAllCars();

    Car getCarById(Long id);

    Car addCar(Car car);
    Car updateCar(Car car);

    boolean deleteCarById(Long id);
    String parseWebPage(String url);



    ;
}
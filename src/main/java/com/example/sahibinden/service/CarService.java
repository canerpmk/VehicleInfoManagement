
package com.example.sahibinden.service;
import com.example.sahibinden.model.entity.CarEntity;


import java.util.List;


public interface CarService {


    List<CarEntity> getAllCars();

    CarEntity getCarById(Long id);

    CarEntity addCar(CarEntity car);

    CarEntity updateCar(CarEntity updatedCar);

    boolean deleteCarById(Long id);
}

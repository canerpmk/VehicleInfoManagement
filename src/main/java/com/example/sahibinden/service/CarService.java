
package com.example.sahibinden.service;

import com.example.sahibinden.model.Entity.CarEntity;
import com.example.sahibinden.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;

    }

    public CarEntity getCarById(int id) {
        return carRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id));
    }

    public List<CarEntity> getAllCars() {
        return carRepository.findAll();

    }

    public CarEntity addCar(CarEntity car) {
        return carRepository.save(car);

    }

    public CarEntity updateCar(CarEntity updatedCar) {
        if (carRepository.existsById(updatedCar.getId())) {
            return carRepository.save(updatedCar);
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: "+updatedCar.getModel());
    }

    public boolean deleteCarById(int id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id);
    }

}

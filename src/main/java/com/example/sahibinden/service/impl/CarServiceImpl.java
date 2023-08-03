package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.entity.CarEntity;
import com.example.sahibinden.repository.CarRepository;
import com.example.sahibinden.service.CarService;
import com.example.sahibinden.service.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;


    public CarEntity getCarById(Long id) {
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

    public boolean deleteCarById(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id);
    }
}

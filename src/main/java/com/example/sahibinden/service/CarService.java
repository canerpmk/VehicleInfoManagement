
package com.example.sahibinden.service;

import com.example.sahibinden.CustomException;
import com.example.sahibinden.model.CarModel;
import com.example.sahibinden.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarModel getCarById(int id) {
        return carRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id));
    }

    public List<CarModel> getAllCars() {
        return carRepository.findAll();

    }

    public CarModel addCar(CarModel car) {
        return carRepository.save(car);

    }

    public CarModel updateCar(CarModel updatedCar) {
        if (carRepository.existsById(updatedCar.getModel_id())) {
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

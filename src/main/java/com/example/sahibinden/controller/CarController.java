
package com.example.sahibinden.controller;

import com.example.sahibinden.model.entity.CarEntity;
import com.example.sahibinden.service.CarService;
import com.example.sahibinden.service.impl.CarServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/car")
public class CarController {
    private final CarServiceImpl carService;

    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarEntity>> getAllCars() {
        List<CarEntity> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarEntity> getCarById(@PathVariable Long id) {
        CarEntity car = carService.getCarById(id);
        if (car != null) {
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Araba ekle
    @PostMapping
    public ResponseEntity<CarEntity> createCar(@RequestBody CarEntity car) {
        CarEntity createdCar = carService.addCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    // Belirli bir araba ID'sine göre arabayı güncelle
    @PutMapping("/{id}")
    public ResponseEntity<CarEntity> updateCar(@PathVariable Long id, @RequestBody CarEntity updatedCar) {
        updatedCar.setId(id);
        CarEntity updatedCarResult = carService.updateCar(updatedCar);
        if (updatedCarResult != null) {
            return ResponseEntity.ok(updatedCarResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        boolean isDeleted = carService.deleteCarById(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

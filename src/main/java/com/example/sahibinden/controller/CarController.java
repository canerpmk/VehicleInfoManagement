
package com.example.sahibinden.controller;

import com.example.sahibinden.model.CarModel;
import com.example.sahibinden.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/car")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarModel>> getAllCars() {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarModel> getCarById(@PathVariable int id) {
        CarModel car = carService.getCarById(id);
        return ResponseEntity.ok(car);

    }

    @PostMapping
    public ResponseEntity<CarModel> createCar(@RequestBody CarModel car) {
        CarModel CreatedCar = carService.addCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreatedCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModel> updateCar(@PathVariable int id, @RequestBody CarModel updatedCar) {
        updatedCar.setModel_id(id);
        CarModel updatedNoteResult = carService.updateCar(updatedCar);
        if (updatedNoteResult != null) {
            return ResponseEntity.ok(updatedNoteResult);
        } else {
            return ResponseEntity.status(updatedNoteResult == null ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        boolean isDeleted = carService.deleteCarById(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(isDeleted ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
        }
    }


}

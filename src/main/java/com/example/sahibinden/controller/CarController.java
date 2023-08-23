package com.example.sahibinden.controller;

import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.dto.CarRequest;
import com.example.sahibinden.model.dto.CarResponse;
import com.example.sahibinden.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/car")
public class CarController {
    private final CarService carService;


    @GetMapping
    public ResponseEntity<List<CarResponse>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        List<CarResponse> carResponses = cars.stream()
                .map(CarResponse::fromModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(carResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        CarResponse carResponse = CarResponse.fromModel(car);
        return ResponseEntity.ok(carResponse);
    }

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest) {
        Car car = carRequest.toModel();
        CarResponse createdCar = CarResponse.fromModel(carService.addCar(car));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateMarka(@PathVariable Long id, @RequestBody CarRequest carRequest) {
        Car car = carRequest.toModel();
        Car updatedCar = carService.updateCar(car);
        CarResponse carResponse = CarResponse.fromModel(updatedCar);
        return ResponseEntity.ok(carResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }


}
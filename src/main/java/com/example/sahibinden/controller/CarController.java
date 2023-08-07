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
        if (car != null) {
            CarResponse carResponse = CarResponse.fromModel(car);
            return ResponseEntity.ok(carResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest) {
        Car car = carRequest.toModel();
        CarResponse createdCar = CarResponse.fromModel(carService.addCar(car));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }


}
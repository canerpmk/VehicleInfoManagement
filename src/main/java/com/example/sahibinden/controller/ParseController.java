package com.example.sahibinden.controller;

import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.dto.CarRequest;
import com.example.sahibinden.model.dto.CarResponse;
import com.example.sahibinden.service.CarService;
import com.example.sahibinden.service.HtmlParserService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParseController {
    private final HtmlParserService htmlParserService;
    private final CarService carService;


    @GetMapping("/parse")
    public ResponseEntity<String> parseWebPage() {
        String url = "http://arabamkacyakar.com/alfa-romeo/1";
        String parsedData = carService.parseWebPage(url);
        return ResponseEntity.ok(parsedData);
    }

    @PostMapping("/x")
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest) {
        String url = "http://arabamkacyakar.com/alfa-romeo/1";
        String parsedData = carService.parseWebPage(url);

        Car car = carRequest.toModel();
        CarResponse createdCar = CarResponse.fromModel(carService.addCar(car));
       // return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
        return ResponseEntity.ok(createdCar);
    }

}

package com.example.sahibinden.controller;

import com.example.sahibinden.model.Motor;
import com.example.sahibinden.model.dto.MotorRequest;
import com.example.sahibinden.model.dto.MotorResponse;
import com.example.sahibinden.service.MotorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/motor")
@RequiredArgsConstructor

public class MotorController {
    private final MotorService motorService;

    @GetMapping("/{id}")
    public ResponseEntity<MotorResponse> getMotorById(@PathVariable Long id) {
        Motor motor = motorService.getMotorById(id);
        MotorResponse motorResponse = MotorResponse.fromModel(motor);
        return ResponseEntity.ok(motorResponse);
    }

    @GetMapping
    public ResponseEntity<List<MotorResponse>> getAllMotorlar() {
        List<Motor> motorlar = motorService.getAllMotor();
        List<MotorResponse> motorResponses = motorlar.stream()
                .map(MotorResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(motorResponses);
    }

    @GetMapping("/parse")
    public ResponseEntity<List<String>> parseWebPage() {
        String url = "http://arabamkacyakar.com/alfa-romeo/1";
        List<String> parsedDataList = motorService.parseWebPage(url);
        return ResponseEntity.ok(parsedDataList);
    }

    @PostMapping
    public ResponseEntity<MotorResponse> addMotor(@RequestBody MotorRequest motorRequest) {

        Motor motor = motorRequest.toModel();
        Motor addedMotor = motorService.addMotor(motor);
        MotorResponse motorResponse = MotorResponse.fromModel(addedMotor);
        return ResponseEntity.status(HttpStatus.CREATED).body(motorResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotorResponse> updateMotor(@PathVariable Long id, @RequestBody MotorRequest motorRequest) {
        Motor motor = motorRequest.toModel();
        motor.setId(id);
        Motor updatedMotor = motorService.updateMotor(motor);

        MotorResponse motorResponse = MotorResponse.fromModel(updatedMotor);
        if (updatedMotor != null) {
            return ResponseEntity.ok(motorResponse);
        } else {
            return ResponseEntity.status(updatedMotor == null ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotor(@PathVariable Long id) {
        motorService.deleteMotorById(id);
        return ResponseEntity.noContent().build();
    }
}

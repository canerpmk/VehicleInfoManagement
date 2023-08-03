package com.example.sahibinden.controller;

import com.example.sahibinden.model.entity.MotorEntity;
import com.example.sahibinden.service.impl.MotorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/motor")
public class MotorController {
    private final MotorServiceImpl motorService;

    public MotorController(MotorServiceImpl motorService) {
        this.motorService = motorService;
    }

    @GetMapping
    public ResponseEntity<List<MotorEntity>> getAllMotor() {
        List<MotorEntity> motorler = motorService.getAllMotor();
        return ResponseEntity.ok(motorler);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotorEntity> getMotorById(@PathVariable Long id) {
        MotorEntity motor = motorService.getMotorById(id);
        if (motor != null) {
            return ResponseEntity.ok(motor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MotorEntity> createMotor(@RequestBody MotorEntity motor) {
        MotorEntity createdMotor = motorService.addMotor(motor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMotor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotorEntity> updateMotor(@PathVariable Long id, @RequestBody MotorEntity updatedMotor) {
        updatedMotor.setId(id);
        MotorEntity updatedMotorResult = motorService.updateMotor(updatedMotor);
        if (updatedMotorResult != null) {
            return ResponseEntity.ok(updatedMotorResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotor(@PathVariable Long id) {
        boolean isDeleted = motorService.deleteMotorById(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

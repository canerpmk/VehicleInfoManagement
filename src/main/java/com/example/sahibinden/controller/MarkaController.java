package com.example.sahibinden.controller;

import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.service.impl.MarkaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/marka")

public class MarkaController {
    private final MarkaServiceImpl markaService;

    public MarkaController(MarkaServiceImpl markaService) {
        this.markaService = markaService;
    }

    @GetMapping
    public ResponseEntity<List<MarkaEntity>> getAllMarka() {
        List<MarkaEntity> markalar = markaService.getAllMarka();
        return ResponseEntity.ok(markalar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarkaEntity> getMarkaById(@PathVariable Long id) {
        MarkaEntity marka = markaService.getMarkaById(id);
        if (marka != null) {
            return ResponseEntity.ok(marka);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<MarkaEntity> createMarka(@RequestBody MarkaEntity marka) {
        MarkaEntity createdMarka = markaService.addMarka(marka);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMarka);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MarkaEntity> updateMarka(@PathVariable Long id, @RequestBody MarkaEntity updatedMarka) {
        updatedMarka.setId(id);
        MarkaEntity updatedMarkaResult = markaService.updateMarka(updatedMarka);
        if (updatedMarkaResult != null) {
            return ResponseEntity.ok(updatedMarkaResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarka(@PathVariable Long id) {
        boolean isDeleted = markaService.deleteMarkaById(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

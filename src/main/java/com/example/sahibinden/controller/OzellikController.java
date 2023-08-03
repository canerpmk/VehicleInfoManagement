package com.example.sahibinden.controller;

import com.example.sahibinden.model.entity.OzellikEntity;
import com.example.sahibinden.service.impl.OzellikServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ozellik")
public class OzellikController {
    private final OzellikServiceImpl ozellikService;

    public OzellikController(OzellikServiceImpl ozellikService) {
        this.ozellikService = ozellikService;
    }

    @GetMapping
    public ResponseEntity<List<OzellikEntity>> getAllOzellik() {
        List<OzellikEntity> ozellikler = ozellikService.getAllOzellik();
        return ResponseEntity.ok(ozellikler);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OzellikEntity> getOzellikById(@PathVariable Long id) {
        OzellikEntity ozellik = ozellikService.getOzellikById(id);
        if (ozellik != null) {
            return ResponseEntity.ok(ozellik);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OzellikEntity> createOzellik(@RequestBody OzellikEntity ozellik) {
        OzellikEntity createdOzellik = ozellikService.addOzellik(ozellik);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOzellik);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OzellikEntity> updateOzellik(@PathVariable Long id, @RequestBody OzellikEntity updatedOzellik) {
        updatedOzellik.setId(id);
        OzellikEntity updatedOzellikResult = ozellikService.updateOzellik(updatedOzellik);
        if (updatedOzellikResult != null) {
            return ResponseEntity.ok(updatedOzellikResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOzellik(@PathVariable Long id) {
        boolean isDeleted = ozellikService.deleteOzellikById(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

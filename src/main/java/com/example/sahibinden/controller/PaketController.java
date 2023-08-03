package com.example.sahibinden.controller;

import com.example.sahibinden.model.entity.PaketEntity;
import com.example.sahibinden.service.impl.PaketServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/paket")
public class PaketController {
    private final PaketServiceImpl paketService;

    public PaketController(PaketServiceImpl paketService) {
        this.paketService = paketService;
    }

    @GetMapping
    public ResponseEntity<List<PaketEntity>> getAllPaket() {
        List<PaketEntity> paketler = paketService.getAllPaket();
        return ResponseEntity.ok(paketler);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaketEntity> getPaketById(@PathVariable Long id) {
        PaketEntity paket = paketService.getPaketById(id);
        if (paket != null) {
            return ResponseEntity.ok(paket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PaketEntity> createPaket(@RequestBody PaketEntity paket) {
        PaketEntity createdPaket = paketService.addPaket(paket);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPaket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaketEntity> updatePaket(@PathVariable Long id, @RequestBody PaketEntity updatedPaket) {
        updatedPaket.setId(id);
        PaketEntity updatedPaketResult = paketService.updatePaket(updatedPaket);
        if (updatedPaketResult != null) {
            return ResponseEntity.ok(updatedPaketResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaket(@PathVariable Long id) {
        boolean isDeleted = paketService.deletePaketById(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

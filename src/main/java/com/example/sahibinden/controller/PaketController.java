package com.example.sahibinden.controller;

import com.example.sahibinden.model.Paket;
import com.example.sahibinden.model.dto.PaketRequest;
import com.example.sahibinden.model.dto.PaketResponse;
import com.example.sahibinden.model.entity.PaketEntity;
import com.example.sahibinden.service.impl.PaketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/paket")
@RequiredArgsConstructor
public class PaketController {
    private final PaketServiceImpl paketService;

    @GetMapping("/{id}")
    public ResponseEntity<PaketResponse> getPaketById(@PathVariable Long id) {
        Paket paket = paketService.getPaketById(id);
        PaketResponse paketResponse = PaketResponse.fromModel(paket);
        return ResponseEntity.ok(paketResponse);
    }

    @GetMapping
    public ResponseEntity<List<PaketResponse>> getAllPaketler() {
        List<Paket> paketler = paketService.getAllPaket();
        List<PaketResponse> paketResponses = paketler.stream()
                .map(PaketResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paketResponses);
    }
    @GetMapping("/parse")
    public ResponseEntity<List<String>> parseWebPage() {
        String url = "http://arabamkacyakar.com/alfa-romeo/1";
        List<String> parsedDataList = paketService.parseWebPage(url);
        return ResponseEntity.ok(parsedDataList);
    }

    @PostMapping
    public ResponseEntity<PaketResponse> addPaket(@RequestBody PaketRequest paketRequest) {
        Paket paket = paketRequest.toModel();
        Paket addedPaket = paketService.addPaket(paket);
        PaketResponse paketResponse = PaketResponse.fromModel(addedPaket);
        return ResponseEntity.status(HttpStatus.CREATED).body(paketResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaketResponse> updatePaket(@PathVariable Long id, @RequestBody PaketRequest paketRequest) {
        Paket paket = paketRequest.toModel();
        paket.setId(id);
        Paket updatedPaket = paketService.updatePaket(paket);

        PaketResponse paketResponse = PaketResponse.fromModel(updatedPaket);
        if (updatedPaket != null) {
            return ResponseEntity.ok(paketResponse);
        } else {
            return ResponseEntity.status(updatedPaket == null ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaket(@PathVariable Long id) {
        paketService.deletePaketById(id);
        return ResponseEntity.noContent().build();
    }
}

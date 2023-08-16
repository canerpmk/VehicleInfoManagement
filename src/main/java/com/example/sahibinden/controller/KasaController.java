package com.example.sahibinden.controller;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.dto.KasaRequest;
import com.example.sahibinden.model.dto.KasaResponse;
import com.example.sahibinden.service.KasaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/kasa")
@RequiredArgsConstructor
public class KasaController {
    private final KasaService kasaService;

    @GetMapping("/{id}")
    public ResponseEntity<KasaResponse> getKasaById(@PathVariable Long id) {
        Kasa kasa = kasaService.getKasaById(id);
        KasaResponse kasaResponse = KasaResponse.fromModel(kasa);
        return ResponseEntity.ok(kasaResponse);
    }
    @GetMapping("/parse")
    public ResponseEntity<List<Kasa>> parseWebPage() {

        String domain ="http://arabamkacyakar.com/";
        String markaPagePath ="audi/a1/1";
        List<Kasa> dataFromUrl = kasaService.parseWebPage(domain,markaPagePath);


        return ResponseEntity.ok(dataFromUrl);
    }

    @GetMapping
    public ResponseEntity<List<KasaResponse>> getAllKasalar() {
        List<Kasa> kasalar = kasaService.getAllKasa();
        List<KasaResponse> kasaResponses = kasalar.stream().map(KasaResponse::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(kasaResponses);
    }


    @PostMapping
    public ResponseEntity<KasaResponse> addKasa(@RequestBody KasaRequest kasaRequest) {
        Kasa kasa = kasaRequest.toModel();
        Kasa addedKasa = kasaService.addKasa(kasa);
        KasaResponse kasaResponse = KasaResponse.fromModel(addedKasa);
        return ResponseEntity.status(HttpStatus.CREATED).body(kasaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KasaResponse> updateKasa(@PathVariable Long id, @RequestBody KasaRequest kasaRequest) {
        Kasa kasa = kasaRequest.toModel();
        kasa.setId(id);
        Kasa updatedKasa = kasaService.updateKasa(kasa);
        KasaResponse kasaResponse = KasaResponse.fromModel(updatedKasa);

        if (updatedKasa != null) {
            return ResponseEntity.ok(kasaResponse);
        } else {
            return ResponseEntity.status(updatedKasa == null ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKasa(@PathVariable Long id) {
        kasaService.deleteKasaById(id);
        return ResponseEntity.noContent().build();
    }
}

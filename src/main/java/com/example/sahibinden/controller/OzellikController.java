package com.example.sahibinden.controller;

import com.example.sahibinden.model.Ozellik;
import com.example.sahibinden.model.dto.OzellikRequest;
import com.example.sahibinden.model.dto.OzellikResponse;
import com.example.sahibinden.service.OzellikService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/ozellik")
@RequiredArgsConstructor
public class OzellikController {
    private final OzellikService ozellikService;

    @GetMapping("/{id}")
    public ResponseEntity<OzellikResponse> getOzellikById(@PathVariable Long id) {
        Ozellik ozellik = ozellikService.getOzellikById(id);
        OzellikResponse ozellikResponse = OzellikResponse.fromModel(ozellik);
        return ResponseEntity.ok(ozellikResponse);
    }

    @GetMapping
    public ResponseEntity<List<OzellikResponse>> getAllOzellikler() {
        List<Ozellik> ozellikler = ozellikService.getAllOzellik();
        List<OzellikResponse> ozellikResponses = ozellikler.stream()
                .map(OzellikResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ozellikResponses);
    }

    @PostMapping
    public ResponseEntity<OzellikResponse> addOzellik(@RequestBody OzellikRequest ozellikRequest) {
        Ozellik ozellik = ozellikRequest.toModel();
        Ozellik addedOzellik = ozellikService.addOzellik(ozellik);
        OzellikResponse ozellikResponse = OzellikResponse.fromModel(addedOzellik);
        return ResponseEntity.status(HttpStatus.CREATED).body(ozellikResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OzellikResponse> updateOzellik(@PathVariable Long id, @RequestBody OzellikRequest ozellikRequest) {
        Ozellik ozellik = ozellikRequest.toModel();
        ozellik.setId(id);
        Ozellik updatedOzellik = ozellikService.updateOzellik(ozellik);

        OzellikResponse ozellikResponse = OzellikResponse.fromModel(updatedOzellik);
        if (updatedOzellik != null) {
            return ResponseEntity.ok(ozellikResponse);
        } else {
            return ResponseEntity.status(updatedOzellik == null ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOzellik(@PathVariable Long id) {
        ozellikService.deleteOzellikById(id);
        return ResponseEntity.noContent().build();
    }
}

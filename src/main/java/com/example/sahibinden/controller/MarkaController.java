package com.example.sahibinden.controller;

import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.dto.CarResponse;
import com.example.sahibinden.model.dto.MarkaRequest;
import com.example.sahibinden.model.dto.MarkaResponse;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.service.MarkaService;
import com.example.sahibinden.service.impl.MarkaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/marka")
@RequiredArgsConstructor
public class MarkaController {
    private final MarkaService markaService;

    @GetMapping("/{id}")
    public ResponseEntity<MarkaResponse> getMarkaById(@PathVariable Long id) {
        Marka marka = markaService.getMarkaById(id);
        MarkaResponse markaResponse = MarkaResponse.fromModel(marka);
        return ResponseEntity.ok(markaResponse);
    }

    @GetMapping
    public ResponseEntity<List<MarkaResponse>> getAllMarkalar() {
        List<Marka> markalar = markaService.getAllMarka();
        List<MarkaResponse> markaResponses = markalar.stream().map(MarkaResponse::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(markaResponses);
    }
    @GetMapping("/parse")
    public ResponseEntity<List<Marka>> parseWebPage() {

        String domain ="http://arabamkacyakar.com/";
        String markaPagePath ="markalar";
        List<Marka> dataFromUrl = markaService.parseWebPage(domain,markaPagePath);


        return ResponseEntity.ok(dataFromUrl);
    }
    @PostMapping
    public ResponseEntity<MarkaResponse> addMarka(@RequestBody MarkaRequest markaRequest) {

        Marka marka = markaRequest.toModel();
        Marka addedMarka = markaService.addMarka(marka);
        MarkaResponse markaResponse = MarkaResponse.fromModel(addedMarka);
        return ResponseEntity.status(HttpStatus.CREATED).body(markaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarkaResponse> updateMarka(@PathVariable Long id, @RequestBody MarkaRequest markaRequest) {

        Marka marka = markaRequest.toModel();
        marka.setId(id);
        Marka updatedMarka = markaService.updateMarka(marka);

        MarkaResponse markaResponse = MarkaResponse.fromModel(updatedMarka);
        if (updatedMarka!=null) {

            return ResponseEntity.ok(markaResponse);
        }
        else{
            return ResponseEntity.status(updatedMarka==null ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarka(@PathVariable Long id) {
        markaService.deleteMarkaById(id);
        return ResponseEntity.noContent().build();
    }


}
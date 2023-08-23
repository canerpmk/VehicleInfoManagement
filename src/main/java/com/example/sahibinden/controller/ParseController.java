package com.example.sahibinden.controller;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Motor;
import com.example.sahibinden.model.Ozellik;
import com.example.sahibinden.model.dto.MarkaResponse;
import com.example.sahibinden.model.entity.KasaEntity;
import com.example.sahibinden.model.entity.ModelEntity;
import com.example.sahibinden.model.entity.MotorEntity;
import com.example.sahibinden.service.ParseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/parse")
@RequiredArgsConstructor
public class ParseController {
    private final ParseService parseService;


    @GetMapping("/marka")
    public ResponseEntity<List<Marka>> parseMarkaPage() {

        List<Marka> dataFromUrl = parseService.parseMarkaPage();
        return ResponseEntity.ok(dataFromUrl);
    }

    @GetMapping("/marka/update")
    public ResponseEntity<List<MarkaResponse>> updateMarkaPage() {
        List<MarkaResponse> updatedMarkaList = parseService.updateMarkas().stream().map(MarkaResponse::fromModel).toList();
        return ResponseEntity.ok(updatedMarkaList);
    }

    @GetMapping("/model/{markaPagePath}/update")
    public ResponseEntity<List<ModelEntity>> updateModelPage(@PathVariable("markaPagePath") String markaPagePath) {
        List<ModelEntity> parsedModelList = parseService.updateModels(markaPagePath).stream().map(ModelEntity::fromModel).toList();
        return ResponseEntity.ok(parsedModelList);
    }

    @GetMapping("/model/{markaPagePath}")
    public ResponseEntity<List<ModelEntity>> parseModelPage(@PathVariable("markaPagePath") String markaPagePath) {
        List<ModelEntity> parsedModelList = parseService.parseModelPage(markaPagePath).stream().map(ModelEntity::fromModel).toList();
        return ResponseEntity.ok(parsedModelList);
    }


    @GetMapping("/kasa/{markaPagePath}/{modelPagePath}")
    public ResponseEntity<List<KasaEntity>> parseKasaPage(@PathVariable("markaPagePath") String markaPagePath, @PathVariable("modelPagePath") String modelPagePath) {
        List<KasaEntity> parsedKasaList = parseService.parseKasaPage(markaPagePath, modelPagePath).stream().map(KasaEntity::fromModel).toList();
        return ResponseEntity.ok(parsedKasaList);
    }

    @GetMapping("/kasa/{markaPagePath}/{modelPagePath}/update")
    public ResponseEntity<List<KasaEntity>> updateKasaPage(@PathVariable("markaPagePath") String markaPagePath, @PathVariable("modelPagePath") String modelPagePath) {
        List<KasaEntity> parsedKasaList = parseService.updateKasas(markaPagePath, modelPagePath).stream().map(KasaEntity::fromModel).toList();
        return ResponseEntity.ok(parsedKasaList);
    }


    @GetMapping("/motor/{markaPagePath}/{modelPagePath}/{kasaPagePath}/update")
    public ResponseEntity<List<MotorEntity>> updateMotorPage(@PathVariable("markaPagePath") String markaPagePath, @PathVariable("modelPagePath") String modelPagePath, @PathVariable String kasaPagePath) {
        List<MotorEntity> parsedKasaList = parseService.updateMotors(markaPagePath, modelPagePath, kasaPagePath).stream().map(MotorEntity::fromModel).toList();
        return ResponseEntity.ok(parsedKasaList);
    }


    @GetMapping("/motor")
    public ResponseEntity<List<Motor>> parseMotorPage() {
        List<Motor> dataFromUrl = parseService.parseMotorrPage();
        return ResponseEntity.ok(dataFromUrl);
    }


    @GetMapping("/kasa")
    public ResponseEntity<List<Kasa>> parseKasaPage() {
        List<Kasa> dataFromUrl = parseService.parseKasaaPage();
        return ResponseEntity.ok(dataFromUrl);
    }

    @GetMapping("/ozellik")
    public ResponseEntity<List<Ozellik>> parseOzellikPage() {

        List<Ozellik> dataFromUrl = parseService.parseOzellikPage();
        return ResponseEntity.ok(dataFromUrl);
    }


}

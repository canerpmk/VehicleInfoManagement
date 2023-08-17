package com.example.sahibinden.controller;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.dto.MarkaResponse;
import com.example.sahibinden.model.entity.ModelEntity;
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

    @GetMapping("/kasa")
    public ResponseEntity<List<Kasa>> parseKasaPage() {
        String kasaPagePath = "audi/a1/1";
        List<Kasa> dataFromUrl = parseService.parseKasaPage(kasaPagePath);
        return ResponseEntity.ok(dataFromUrl);
    }

    @GetMapping("/motor")
    public ResponseEntity<List<String>> parseMotorPage() {
        String url = "http://arabamkacyakar.com/alfa-romeo/1";
        List<String> parsedDataList = parseService.parseMotorPage(url);
        return ResponseEntity.ok(parsedDataList);
    }

}

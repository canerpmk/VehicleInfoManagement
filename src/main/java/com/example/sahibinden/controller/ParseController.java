package com.example.sahibinden.controller;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Model;
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

        String markaPagePath = "markalar";
        List<Marka> dataFromUrl = parseService.parseMarkaPage(markaPagePath);

        return ResponseEntity.ok(dataFromUrl);
    }

    @GetMapping("/model/{modelPagePath}/1")
    public ResponseEntity<List<Model>> parseModelPage(@PathVariable("modelPagePath") String modelPagePath) {

        modelPagePath += "/1";
        List<Model> dataFromUrl = parseService.parseModelPage(modelPagePath);


        return ResponseEntity.ok(dataFromUrl);
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

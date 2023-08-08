package com.example.sahibinden.controller;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Model;
import com.example.sahibinden.model.dto.MarkaRequest;
import com.example.sahibinden.model.dto.MarkaResponse;
import com.example.sahibinden.model.dto.ModelRequest;
import com.example.sahibinden.model.dto.ModelResponse;
import com.example.sahibinden.model.entity.ModelEntity;
import com.example.sahibinden.service.MarkaService;
import com.example.sahibinden.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/model")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;

    @GetMapping("/{id}")
    public ResponseEntity<ModelResponse> getModelById(@PathVariable Long id) {
        Model model = modelService.getModelById(id);
        ModelResponse modelResponse = ModelResponse.fromModel(model);
        return ResponseEntity.ok(modelResponse);
    }

    @GetMapping
    public ResponseEntity<List<ModelResponse>> getAllModel() {
        List<Model> modeller = modelService.getAllModel();
        List<ModelResponse> modelResponses = modeller.stream()
                .map(ModelResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(modelResponses);
    }

    @PostMapping
    public ResponseEntity<ModelResponse> addModel(@RequestBody ModelRequest modelRequest) {

        Model model = modelRequest.toModel();
        Model addedModel = modelService.addModel(model);
        ModelResponse modelResponse = ModelResponse.fromModel(addedModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelResponse> updateModel(@PathVariable Long id, @RequestBody ModelRequest modelRequest) {

        Model model = modelRequest.toModel();
        Model updatedModel = modelService.updateModel(model);
        ModelResponse modelResponse = ModelResponse.fromModel(updatedModel);
        return ResponseEntity.ok(modelResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long id) {
        modelService.deleteModelById(id);
        return ResponseEntity.noContent().build();
    }
}

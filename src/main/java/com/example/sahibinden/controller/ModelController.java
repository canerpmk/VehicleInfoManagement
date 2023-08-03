package com.example.sahibinden.controller;

import com.example.sahibinden.model.entity.ModelEntity;
import com.example.sahibinden.service.impl.ModelSerivceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/model")

public class ModelController {
    private final ModelSerivceImpl modelService;

    public ModelController(ModelSerivceImpl modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public ResponseEntity<List<ModelEntity>> getAllModel() {
        List<ModelEntity> modeller = modelService.getAllModel();
        return ResponseEntity.ok(modeller);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelEntity> getModelById(@PathVariable Long id) {
        ModelEntity model = modelService.getModelById(id);
        if (model != null) {
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ModelEntity> createModel(@RequestBody ModelEntity model) {
        ModelEntity createdModel = modelService.addModel(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelEntity> updateModel(@PathVariable Long id, @RequestBody ModelEntity updatedModel) {
        updatedModel.setId(id);
        ModelEntity updatedModelResult = modelService.updateModel(updatedModel);
        if (updatedModelResult != null) {
            return ResponseEntity.ok(updatedModelResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long id) {
        boolean isDeleted = modelService.deleteModelById(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.example.sahibinden.service.impl;

import com.example.sahibinden.exception.model.CustomException;
import com.example.sahibinden.model.Model;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.model.entity.ModelEntity;
import com.example.sahibinden.model.entity.MotorEntity;
import com.example.sahibinden.repository.MarkaRepository;
import com.example.sahibinden.repository.ModelRepository;
import com.example.sahibinden.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final MarkaRepository markaService;

    public Model getModelById(Long id) {
        ModelEntity modelEntity = modelRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id));
        return Model.fromEntity(modelEntity);
    }

    public List<Model> getAllModel() {
        List<ModelEntity> modelEntities = modelRepository.findAll();
        return modelEntities.stream()
                .map(Model::fromEntity)
                .collect(Collectors.toList());
    }

    public Model addModel(Model model) {
        MarkaEntity marka=markaService.findById(model.getMarka().getId()).get();
        ModelEntity modelEntity = ModelEntity.fromModel(model);
        modelEntity.setMarka(marka);
        ModelEntity addedModelEntity = modelRepository.save(modelEntity);
        return Model.fromEntity(addedModelEntity);
    }

    public Model updateModel(Model model) {
        if (modelRepository.existsById(model.getId())) {
            ModelEntity updatedModelEntity = modelRepository.save(ModelEntity.fromModel(model));
            return Model.fromEntity(updatedModelEntity);
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + model.getId());
    }

    public boolean deleteModelById(Long id) {
        if (modelRepository.existsById(id)) {
            modelRepository.deleteById(id);
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id);
    }
}

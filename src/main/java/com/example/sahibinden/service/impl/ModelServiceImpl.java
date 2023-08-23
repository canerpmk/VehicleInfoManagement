package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Model;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.model.entity.ModelEntity;
import com.example.sahibinden.repository.MarkaRepository;
import com.example.sahibinden.repository.ModelRepository;
import com.example.sahibinden.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final MarkaRepository markaRepository;

    public Model getModelById(Long id) {
        ModelEntity modelEntity = modelRepository.findById(id).orElseThrow();
        return Model.fromEntity(modelEntity);
    }

    public Model getModelByShortName(String shortName) {
        ModelEntity modelEntity = modelRepository.findModelEntityByShortName(shortName).orElseThrow();
        return Model.fromEntity(modelEntity);
    }

    public List<Model> getAllModel() {
        List<ModelEntity> modelEntities = modelRepository.findAll();
        return modelEntities.stream()
                .map(Model::fromEntity)
                .collect(Collectors.toList());
    }


    public Model addModel(Model model) {
        MarkaEntity marka = markaRepository.findById(model.getMarka().getId()).get();
        ModelEntity modelEntity = ModelEntity.fromModel(model);
        modelEntity.setMarka(marka);
        ModelEntity addedModelEntity = modelRepository.save(modelEntity);
        return Model.fromEntity(addedModelEntity);
    }


    public List<Model> addModels(List<Model> modelList) {
        List<ModelEntity> modelEntityList = modelList.stream().map(ModelEntity::fromModel).toList();
        List<ModelEntity> addedModelEntityList = modelRepository.saveAll(modelEntityList);
        return addedModelEntityList.stream().map(Model::fromEntity).toList();
    }


    public Model updateModel(Model model) {
        if (modelRepository.existsById(model.getId())) {
            ModelEntity updatedModelEntity = modelRepository.save(ModelEntity.fromModel(model));
            return Model.fromEntity(updatedModelEntity);
        }
        return null;
    }

    public boolean deleteModelById(Long id) {
        modelRepository.deleteById(id);
        return !modelRepository.existsById(id);
    }
}
package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.entity.ModelEntity;
import com.example.sahibinden.repository.ModelRepository;
import com.example.sahibinden.exception.model.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelSerivceImpl {
    private final ModelRepository modelRepository;

    public ModelEntity getModelById(Long id) {
        return modelRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id));
    }

    public List<ModelEntity> getAllModel() {
        return modelRepository.findAll();
    }

    public ModelEntity addModel(ModelEntity model) {
        return modelRepository.save(model);
    }

    public ModelEntity updateModel(ModelEntity updatedModel) {
        if (modelRepository.existsById(updatedModel.getId())) {
            return modelRepository.save(updatedModel);
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + updatedModel.getId());
    }

    public boolean deleteModelById(Long id) {
        if (modelRepository.existsById(id)) {
            modelRepository.deleteById(id);
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id);
    }

}

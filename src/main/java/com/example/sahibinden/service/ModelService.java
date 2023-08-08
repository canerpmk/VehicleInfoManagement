package com.example.sahibinden.service;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Model;
import com.example.sahibinden.model.entity.ModelEntity;

import java.util.List;

public interface ModelService {
    Model getModelById(Long id);
    List<Model> getAllModel();
    Model addModel(Model model);
    Model updateModel(Model model);
    boolean deleteModelById(Long id);

}

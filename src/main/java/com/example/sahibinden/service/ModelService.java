package com.example.sahibinden.service;

import com.example.sahibinden.model.Model;

import java.util.List;

public interface ModelService {
    List<Model> getAllModel();

    Model getModelById(Long id);

    Model addModel(Model model);

    List<Model> addModels(List<Model> modelList);

    Model updateModel(Model updatedModel);

    boolean deleteModelById(Long id);

    List<Model> parseWebPage(String domain, String path);
}

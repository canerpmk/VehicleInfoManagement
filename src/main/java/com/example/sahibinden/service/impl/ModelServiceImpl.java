package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Model;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.model.entity.ModelEntity;
import com.example.sahibinden.repository.MarkaRepository;
import com.example.sahibinden.repository.ModelRepository;
import com.example.sahibinden.exception.model.CustomException;
import com.example.sahibinden.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final MarkaRepository markaRepository;

    public Model getModelById(Long id) {
        ModelEntity modelEntity = modelRepository.findById(id).orElseThrow();
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
    public List<Model> parseWebPage(String domain, String path) {
        List<Model> parseDataList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(domain + path).get();
            Elements modelElements = document.select(".accordion-group2 .accordion-group.selected li ");


                for (Element modelElement : modelElements) {
                    String linkHref = modelElement.attr("href");
                    String linkName = modelElement.text();

                    parseDataList.add(Model.builder()
                            .name(linkName)
                            .shortName(linkHref)
                            .build());
                }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseDataList;
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
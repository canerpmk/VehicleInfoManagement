package com.example.sahibinden.service.impl;
import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Ozellik;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.model.entity.OzellikEntity;
import com.example.sahibinden.repository.OzellikRepository;
import com.example.sahibinden.exception.model.CustomException;
import com.example.sahibinden.service.OzellikService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OzellikServiceImpl implements OzellikService {
    private final OzellikRepository ozellikRepository;

    public Ozellik getOzellikById(Long id) {
        OzellikEntity ozellikEntity = ozellikRepository.findById(id).orElseThrow();
        return Ozellik.fromEntity(ozellikEntity);
    }

    public List<Ozellik> getAllOzellik() {
        List<OzellikEntity> ozellikEntities = ozellikRepository.findAll();
        return ozellikEntities.stream()
                .map(Ozellik::fromEntity)
                .collect(Collectors.toList());
    }

    public Ozellik addOzellik(Ozellik ozellik) {
        OzellikEntity ozellikEntity = OzellikEntity.fromModel(ozellik);
        OzellikEntity addedOzellikEntity = ozellikRepository.save(ozellikEntity);
        return Ozellik.fromEntity(addedOzellikEntity);
    }

    public List<String> parseWebPage(String url) {
        List<String> parsedDataList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(url).get();
            Element seriallist = document.getElementsByClass("seriallist").first();

            for (Element link : seriallist.children()) {
                String linkText = link.text();
                String linkHref = link.attr("href");
                parsedDataList.add("Text: " + linkText + ", URL: " + linkHref);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parsedDataList;
    }
    public Ozellik updateOzellik(Ozellik ozellik) {
        if (ozellikRepository.existsById(ozellik.getId())) {
            OzellikEntity updatedOzellikEntity = ozellikRepository.save(OzellikEntity.fromModel(ozellik));
            return Ozellik.fromEntity(updatedOzellikEntity);
        }
        return null;
    }

    public boolean deleteOzellikById(Long id) {
        ozellikRepository.deleteById(id);
        return !ozellikRepository.existsById(id);
    }
}

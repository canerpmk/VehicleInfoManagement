package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.repository.MarkaRepository;
import com.example.sahibinden.service.MarkaService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarkaServiceImpl implements MarkaService {
    private final MarkaRepository markaRepository;


    public Marka getMarkaById(Long id) {
        MarkaEntity markaEntity = markaRepository.findById(id).orElseThrow();
        return Marka.fromEntity(markaEntity);
    }


    public List<Marka> getAllMarka() {
        List<MarkaEntity> markaEntities = markaRepository.findAll();
        return markaEntities.stream()
                .map(Marka::fromEntity)
                .collect(Collectors.toList());
    }


    public Marka addMarka(Marka marka) {
        MarkaEntity markaEntity = MarkaEntity.fromModel(marka);
        MarkaEntity addedMarkaEntity = markaRepository.save(markaEntity);
        return Marka.fromEntity(addedMarkaEntity);
    }

    public List<Marka> parseWebPage(String domain, String path) {
        List<Marka> parseDataList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(domain + path).get();
            Elements modelList = document
                    .getElementsByClass("results-container-in")
                    .first()
                    .getElementsByClass("col-md-3 brandborder");

            for (Element brandElement : modelList) {
                Element anchorElement = brandElement.select("a").first();
                String linkHref = anchorElement.attr("href");
                linkHref = linkHref == null ? null : linkHref.substring(1, linkHref.length() - 2);
                Element imgElement = brandElement.select("img").first();
                String imgUrl = imgElement.attr("src");
                Element linkTextElement = brandElement.select("img").first();
                String linkText = linkTextElement.attr("alt");

                Element infoElement = brandElement.select("a.btn-xs.btn-default").first();
                String info = infoElement != null ? infoElement.attr("data-content") : "";

                parseDataList.add(Marka.builder()
                        .name(linkText)
                        .shortName(linkHref)
                        .imgUrl(imgUrl)
                        .info(info)
                        .build());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return parseDataList;
    }


    public Marka updateMarka(Marka marka) {
        if (markaRepository.existsById(marka.getId())) {
            MarkaEntity updatedMarkaEntity = markaRepository.save(MarkaEntity.fromModel(marka));
            return Marka.fromEntity(updatedMarkaEntity);
        }
        return null;
    }

    public boolean deleteMarkaById(Long id) {
        markaRepository.deleteById(id);
        return !markaRepository.existsById(id);
    }


}
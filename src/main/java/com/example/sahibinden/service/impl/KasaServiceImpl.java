package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.entity.KasaEntity;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.model.entity.ModelEntity;
import com.example.sahibinden.repository.KasaRepository;
import com.example.sahibinden.repository.MarkaRepository;
import com.example.sahibinden.repository.ModelRepository;
import com.example.sahibinden.service.KasaService;
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
public class KasaServiceImpl implements KasaService {
    private final KasaRepository kasaRepository;
    private final MarkaRepository markaRepository;
    private final ModelRepository modelRepository;

    public Kasa getKasaById(Long id) {
        KasaEntity kasaEntity = kasaRepository.findById(id).orElseThrow();
        return Kasa.fromEntity(kasaEntity);
    }

    public List<Kasa> getAllKasa() {
        List<KasaEntity> kasaEntities = kasaRepository.findAll();
        return kasaEntities.stream()
                .map(Kasa::fromEntity)
                .collect(Collectors.toList());
    }

    public Kasa addKasa(Kasa kasa) {
        MarkaEntity marka = markaRepository.findById(kasa.getMarka().getId()).get();
        ModelEntity model = modelRepository.findById(kasa.getModel().getId()).get();
        KasaEntity kasaEntity = KasaEntity.fromModel(kasa);
        kasaEntity.setMarka(marka);
        kasaEntity.setModel(model);
        KasaEntity addedKasaEntity = kasaRepository.save(kasaEntity);
        return Kasa.fromEntity(addedKasaEntity);

    }

    public List<Kasa> parseWebPage(String domain, String path) {
        List<Kasa> parseDataList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(domain + path).get();
            Elements KasalList = document
                    .getElementsByClass("vehicle-block format-standard");

            for (Element brandElement : KasalList) {
                Element anchorElement = brandElement.select("img").first();
                String linkHref = anchorElement.attr("src");
                Element imgElement = brandElement.select("h4").first();
                String tip = imgElement.text();
                Elements linkTextElement = brandElement.getElementsByClass("vehicle-block-content").first()
                        .select("a");
                String ozelliklink = linkTextElement.attr("href");
                Elements motortipElement = brandElement.getElementsByClass("vehicle-block-content").first()
                        .select("a");
                String motortip = linkTextElement.text();
                parseDataList.add(Kasa.builder()
                        .tip(tip)
                        .imgUrl(linkHref)
                        .ozellik(ozelliklink)
                        .motortip(motortip)
                        .build()

                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseDataList;
    }


    public Kasa updateKasa(Kasa kasa) {
        if (kasaRepository.existsById(kasa.getId())) {
            KasaEntity updatedKasaEntity = kasaRepository.save(KasaEntity.fromModel(kasa));
            return Kasa.fromEntity(updatedKasaEntity);
        }
        return null;
    }

    public boolean deleteKasaById(Long id) {
        kasaRepository.deleteById(id);
        return !kasaRepository.existsById(id);
    }
}

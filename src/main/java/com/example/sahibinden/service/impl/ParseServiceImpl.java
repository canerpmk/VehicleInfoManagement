package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Model;
import com.example.sahibinden.service.MarkaService;
import com.example.sahibinden.service.ModelService;
import com.example.sahibinden.service.ParseService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ParseServiceImpl implements ParseService {
    private final MarkaService markaService;
    private final ModelService modelService;

    private static final String DOMAIN = "http://arabamkacyakar.com/";
    private static final String PATH_MARKA = "markalar";

    public List<Marka> updateMarkas() {
        List<Marka> markaList = parseMarkaPage();
        return markaService.addMarkas(markaList);
    }

    public List<Marka> parseMarkaPage() {
        List<Marka> parseDataList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(DOMAIN + PATH_MARKA).get();
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

    public List<Model> updateModels(String markaShortName) {
        Marka marka = markaService.getMarkaByShortName(markaShortName);
        List<Model> modelList = parseModelPage(markaShortName).stream().peek(model -> model.setMarka(marka)).toList();

        return modelService.addModels(modelList);
    }

    public List<Model> parseModelPage(String markaShortName) {
        List<Model> modelList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(DOMAIN + markaShortName + "/1").get();
            Elements modelElements = document.select(".accordion-group2 .accordion-group.selected li ");


            for (Element modelElement : modelElements) {
                String linkHref = modelElement.attr("href");
                String linkName = modelElement.text();

                modelList.add(Model.builder()
                        .marka(Marka.builder().shortName(markaShortName).build())
                        .name(linkName)
                        .shortName(linkHref)
                        .build());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelList;
    }

    public List<Kasa> parseKasaPage(String path) {
        List<Kasa> parseDataList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(DOMAIN + path).get();
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

    public List<String> parseMotorPage(String url) {
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
}

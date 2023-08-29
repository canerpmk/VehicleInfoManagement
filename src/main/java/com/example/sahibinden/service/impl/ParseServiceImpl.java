package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.*;
import com.example.sahibinden.service.*;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.sahibinden.common.Utils.parseValueWithUnit;

@RequiredArgsConstructor
@Service
public class ParseServiceImpl implements ParseService {
    private final MarkaService markaService;
    private final ModelService modelService;
    private final KasaService kasaService;
    private final MotorService motorService;
    private final OzellikService ozellikService;

    public static final String DOMAIN = "http://arabamkacyakar.com/";
    public static final String PATH_MARKA = "markalar";

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

                Element shortNameElement = modelElement.select("a").first();

                String linkHref = shortNameElement.attr("href");
                String linkName = modelElement.text();
                String[] hrefParts = linkHref.split("/");
                String shortName = null;
                if (hrefParts.length > 1) {
                    shortName = hrefParts[2];
                }

                modelList.add(Model.builder()
                        .marka(Marka.builder().shortName(markaShortName).build())
                        .name(linkName)
                        .shortName(shortName)
                        .build());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelList;
    }


    public List<Kasa> updateKasas(String markaShortName, String modelShortName) {
        Model model = modelService.getModelByShortName(modelShortName);
        List<Kasa> kasaList = parseKasaPage(markaShortName, modelShortName).stream().peek(kasa -> kasa.setModel(model)).toList();
        return kasaService.addKasas(kasaList);
    }

    public List<Kasa> parseKasaPage(String markaShortname, String modelShortName) {
        List<Kasa> parseDataList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(DOMAIN + markaShortname + "/" + modelShortName + "/1").get();
            Elements KasalList = document
                    .getElementsByClass("vehicle-block format-standard");


            for (Element brandElement : KasalList) {
                String kasaShortName = null;
                Element anchorElement = brandElement.select("img").first();
                String linkHref = anchorElement.attr("src");
                Element kasaTipElement = brandElement.select("img").first();
                String kasatip = kasaTipElement.attr("alt");
                Elements linkTextElement = brandElement.getElementsByClass("vehicle-block-content").first()
                        .select("a");
                String motortip = linkTextElement.text();
                Element nameElement = brandElement.getElementsByClass("vehicle-block-content").first().getElementsByClass("vehicle-title text-center").first();
                String yil = null;
                if (nameElement.text().length() >= 11) {
                    yil = nameElement.text().substring(0, 11).replace(" ", "");
                }
                Elements shortNameElements = brandElement.select("a");

                Kasa kasa = Kasa.builder()
                        .yil(yil)
                        .kasaTip(kasatip)
                        .imgUrl(linkHref)
                        .motorTip(motortip)
                        .model(Model.builder().shortName(modelShortName).build())
                        .build();
                for (Element shortNameElement : shortNameElements) {
                    String shortNameHref = shortNameElement.attr("href");
                    String[] link = shortNameHref.split("/");

                    if (link.length > 3 && kasaShortName == null) {
                        kasaShortName = link[3];
                        kasa.setShortName(yil + "_" + kasaShortName);
                    }
                    kasa.addMotor(Motor.builder().shortName(link[4]).name(shortNameElement.text()).build());
                }
                parseDataList.add(kasa);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseDataList;
    }

    public List<Kasa> parseKasaaPage() {
        List<Kasa> parseDataList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(DOMAIN).get();
            Elements KasalList = document
                    .getElementsByClass("vehicle-block format-standard");


            for (Element brandElement : KasalList) {
                Element anchorElement = brandElement.select("img").first();
                String linkHref = anchorElement.attr("src");
                Element imgElement = brandElement.select("h4").first();
                String kasatip = imgElement.text();
                Elements linkTextElement = brandElement.getElementsByClass("vehicle-block-content").first()
                        .select("a");
                String motortip = linkTextElement.text();
                Elements shortNameElement = brandElement.select("a");
                String shortNameHref = shortNameElement.attr("href");
                String[] hrefParts = shortNameHref.split("/");
                String shortName = null;
                if (hrefParts.length > 1) {
                    shortName = "/" + hrefParts[3] + "/" + hrefParts[4];
                }

                parseDataList.add(Kasa.builder()
                        .kasaTip(kasatip)
                        .shortName(shortName)
                        .imgUrl(linkHref)
                        .motorTip(motortip)
                        .build()

                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseDataList;
    }

    public List<Motor> updateMotors(String markaShortName, String modelShortName, String kasaShotName) {
        Kasa kasa = kasaService.getKasaByShortName(kasaShotName);
        List<Motor> motorList = parseMotorPage(markaShortName, modelShortName, kasaShotName)
                .stream()
                .peek(motor -> motor.setKasa(kasa))
                .toList();
        return motorService.addMotors(motorList);
    }

    public List<Motor> parseMotorPage(String markaShortName, String modelShortName, String kasaShortName) {
        List<Motor> parseDataList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(DOMAIN + markaShortName + "/" + modelShortName + "/" + kasaShortName + "/1").get();
            Elements motorList = document.getElementsByClass("dashboard-block").first()
                    .getElementsByClass("table table-bordered ").first().getElementsByClass("borderbottom");

            for (Element motorElement : motorList) {
                Elements imgUrlElemments = motorElement.getElementsByClass("col-md-3 col-sm-4 col-xs-4").select("img");
                String imgUrl = imgUrlElemments.attr("src");
                Elements motorShortNameElements = motorElement.getElementsByClass("col-md-9 col-sm-8 col-xs-8").select("a");

                Element motorYilElements = motorElement.getElementsByClass("col-md-12 col-sm-12 col-xs-12 hidden-lg hidden-md margin-top-5").select("span").get(0);
                String motorYil = motorYilElements.text();

                Elements motorNameElements = motorElement.getElementsByClass("col-md-9 col-sm-8 col-xs-8").select("a");
                String motorName = motorNameElements.text();


                for (Element motorNameElement : motorShortNameElements) {

                    String shortNameHref = motorNameElement.attr("href");
                    String[] link = shortNameHref.split("/");
                    String shortName = null;
                    if (link.length > 1) {
                        shortName = link[3] + "/" + link[4];
                    }

                    Motor motor = Motor.builder()
                            .shortName(shortName)
                            .yil(motorYil)
                            .name(motorName)

                            .build();

                    parseDataList.add(motor);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseDataList;
    }


    public List<Motor> parseMotorrPage() {
        List<Motor> parseDataList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(DOMAIN).get();
            Elements motorList = document.getElementsByClass("dashboard-block").first()
                    .getElementsByClass("table table-bordered ").first().getElementsByClass("borderbottom");

            for (Element motorElement : motorList) {
                Elements imgUrlElemments = motorElement.getElementsByClass("col-md-3 col-sm-4 col-xs-4").select("img");
                String imgUrl = imgUrlElemments.attr("src");
                Elements motorShortNameElements = motorElement.getElementsByClass("col-md-9 col-sm-8 col-xs-8").select("a");

                Element motorYilElements = motorElement.getElementsByClass("col-md-12 col-sm-12 col-xs-12 hidden-lg hidden-md margin-top-5").select("span").get(0);
                String motorYil = motorYilElements.text();

                Elements motorNameElements = motorElement.getElementsByClass("col-md-9 col-sm-8 col-xs-8").select("a");
                String motorName = motorNameElements.text();


                for (Element motorNameElement : motorShortNameElements) {

                    String shortNameHref = motorNameElement.attr("href");
                    String[] link = shortNameHref.split("/");
                    String shortName = null;
                    if (link.length > 1) {
                        shortName = link[3] + "/" + link[4];
                    }

                    Motor motor = Motor.builder()
                            .shortName(shortName)
                            .yil(motorYil)
                            .name(motorName)

                            .build();

                    parseDataList.add(motor);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseDataList;
    }


    public List<Ozellik> parseOzellikPage() {
        List<Ozellik> parseDataList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(DOMAIN).get();
            Elements ozellikList = document.
                    getElementsByClass("col-md-4").first()
                    .getElementsByClass("sidebar-widget widget")
                    .first()
                    .getElementsByClass("list-group");

            Elements motorNameElements = document.getElementsByClass("col-md-12").first()
                    .getElementsByClass("breadcrumb").select("li").get(4).select("a span");
            Elements motorSilindirSayisiElements = document
                    .getElementsByClass("col-md-6 margin-top-10").get(2)
                    .getElementsByClass("accordion-inner").first()
                    .getElementsByClass("list-group").first()
                    .select("li")
                    .first()
                    .getElementsByClass("badgeright");
            String motorSilindirSayisistr = motorSilindirSayisiElements.text();
            Double motorSilindirSayi = parseValueWithUnit(motorSilindirSayisistr);

            Elements bagajHacmiElements = document
                    .getElementsByClass("accordion-body collapse in").get(2)
                    .getElementsByClass("accordion-inner").first()
                    .getElementsByClass("list-group").first()
                    .getElementsByClass("list-group-item-li").get(7)
                    .select("span");
            String bagajHacmistr = bagajHacmiElements.text();
            Integer bagajHacmi = (int) parseValueWithUnit(bagajHacmistr);

            Elements yakitList = document.getElementsByClass("col-md-6 margin-top-10").get(0)
                    .getElementsByClass("table-responsive fuelbox").first()
                    .select("tbody");
            Element si_OrtTuketimElement = yakitList.select("tr")
                    .first()
                    .select("td").get(1).select("strong").first();
            String si_OrtTuketim = si_OrtTuketimElement.text();

            Element sd_OrtTuketimElement = yakitList.select("tr")
                    .get(1)
                    .select("td").get(1).select("strong").first();
            String sd_OrtTuketim = sd_OrtTuketimElement.text();
            Element ortTuketimElement = yakitList.select("tr")
                    .get(2)
                    .select("td").get(1).select("strong").first();
            String ortTuketim = ortTuketimElement.text();


            for (Element ozellikElement : ozellikList) {
                Elements maxHizElement = ozellikElement.getElementsByClass("list-group-item-li").get(9).select("span");
                String maxHizstr = maxHizElement.text();
                Double maxHiz = parseValueWithUnit(maxHizstr);
                Elements yakitTurElement = ozellikElement.getElementsByClass("list-group-item-li").get(10).select("span");
                String yakitTur = yakitTurElement.text();
                Element yakitDeposuElement = ozellikElement.getElementsByClass("list-group-item-li").get(11).select("span.badgeright").first();
                String yakitDeposuStr = yakitDeposuElement.text();
                Double yakitDeposu = parseValueWithUnit(yakitDeposuStr);
                Element vitesKutusuElement = ozellikElement.getElementsByClass("list-group-item-li").get(7).select("span.badgeright").first();
                String vitesKutusu = vitesKutusuElement.text();


                Element torkElement = ozellikElement.getElementsByClass("list-group-item-li").get(6).select("span.badgeright").first();
                String torkStr = torkElement.text();
                Double tork = parseValueWithUnit(torkStr);


                Elements motorHacmiElement = ozellikElement.getElementsByClass("list-group-item-li").get(4).select("span");
                String motorHacmistr = motorHacmiElement.text();
                Double motorHacmi = parseValueWithUnit(motorHacmistr);


                Elements motorGucuElement = ozellikElement.getElementsByClass("list-group-item-li").get(5).select("span");
                String motorGucustr = motorGucuElement.text();
                Double motorGucu = parseValueWithUnit(motorGucustr);


                Ozellik ozellik = Ozellik.builder()
                        .yakit_tur(yakitTur)
                        .bagaj_hacmi(bagajHacmi)
                        .max_hiz(maxHiz).ort_tuketim(ortTuketim)
                        .si_tuketim(si_OrtTuketim)
                        .sd_tuketim(sd_OrtTuketim)
                        .yakit_deposu(yakitDeposu)
                        .vites_kutusu(vitesKutusu)
                        .silindirsayisi(motorSilindirSayi)
                        .tork(tork)
                        .motorhacmi(motorHacmi)
                        .motorgucu(motorGucu)
                        .build();

                parseDataList.add(ozellik);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseDataList;
    }


}



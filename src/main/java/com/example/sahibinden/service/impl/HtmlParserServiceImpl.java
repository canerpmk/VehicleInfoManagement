package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Car;
import com.example.sahibinden.service.HtmlParserService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class HtmlParserServiceImpl implements HtmlParserService {


    public String parseWebPage(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.select("a");

            StringBuilder response = new StringBuilder();
            for (Element link : links) {
                String linkText = link.text();
                String linkHref = link.attr("href");
                response.append("Text: ").append(linkText).append(", URL: ").append(linkHref).append("\n");
            }

            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while parsing the web page.";
        }
    }

}

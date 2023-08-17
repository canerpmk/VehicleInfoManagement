package com.example.sahibinden.service;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Model;

import java.util.List;

public interface ParseService {
    List<Marka> parseMarkaPage();

    List<Marka> updateMarkas();

    List<Model> parseModelPage(String markaShortName);

    List<Model> updateModels(String markaShortName);


    List<Kasa> parseKasaPage(String kasaPagePath);

    List<String> parseMotorPage(String url);
}

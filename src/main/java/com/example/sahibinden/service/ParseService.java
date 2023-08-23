package com.example.sahibinden.service;

import com.example.sahibinden.model.*;

import java.util.List;

public interface ParseService {

    List<Marka> updateMarkas();

    List<Model> updateModels(String markaShortName);

    List<Kasa> updateKasas(String markaPagePath, String modelShortName);

    List<Motor> updateMotors(String markaShortName, String modelShortName, String kasaShortName);


    List<Motor> parseMotorrPage();


    List<Marka> parseMarkaPage();

    List<Model> parseModelPage(String markaShortName);

    List<Kasa> parseKasaPage(String markaShortname, String modelShortName);

    List<Motor> parseMotorPage(String markaShortName, String modelShortName, String kasaShortName);


    List<Kasa> parseKasaaPage();

    List<Ozellik> parseOzellikPage();
}

package com.example.sahibinden.service;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.Marka;

import java.util.List;

public interface KasaService {

    Kasa getKasaById(Long id);

    List<Kasa> getAllKasa();

    Kasa addKasa(Kasa kasa);

    Kasa updateKasa(Kasa kasa);

    boolean deleteKasaById(Long id);
    List<Kasa> parseWebPage(String domain, String path);
}

package com.example.sahibinden.service;

import com.example.sahibinden.model.Kasa;

import java.util.List;

public interface KasaService {

    Kasa getKasaById(Long id);

    List<Kasa> getAllKasa();

    Kasa addKasa(Kasa kasa);

    Kasa updateKasa(Kasa kasa);

    boolean deleteKasaById(Long id);

    List<Kasa> addKasas(List<Kasa> kasaList);

    Kasa getKasaByShortName(String shortName);

}

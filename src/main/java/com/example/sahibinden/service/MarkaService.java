package com.example.sahibinden.service;

import com.example.sahibinden.model.Marka;

import java.util.List;


public interface MarkaService {

    List<Marka> getAllMarka();

    Marka getMarkaById(Long id);

    Marka getMarkaByShortName(String shortName);

    Marka addMarka(Marka marka);

    List<Marka> addMarkas(List<Marka> markaList);

    Marka updateMarka(Marka updatedMarka);

    boolean deleteMarkaById(Long id);

    List<Marka> parseWebPage(String domain, String path);
}
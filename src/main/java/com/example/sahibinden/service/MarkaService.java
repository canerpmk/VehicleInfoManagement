package com.example.sahibinden.service;

import com.example.sahibinden.model.Marka;

import java.util.List;


public interface MarkaService {

    List<Marka> getAllMarka();

    Marka getMarkaById(Long id);

    Marka addMarka(Marka marka);

    Marka updateMarka(Marka updatedMarka);

    boolean deleteMarkaById(Long id);
}
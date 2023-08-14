package com.example.sahibinden.service;
import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.entity.MarkaEntity;

import java.util.List;


public interface MarkaService {

    List<Marka> getAllMarka();

    Marka getMarkaById(Long id);

    Marka addMarka(Marka marka);

    Marka updateMarka(Marka updatedMarka);

    boolean deleteMarkaById(Long id);

}
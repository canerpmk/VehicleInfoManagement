package com.example.sahibinden.service;
import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.entity.MarkaEntity;

import java.util.List;


public interface MarkaService {

    List<MarkaEntity> getAllMarka();

    MarkaEntity getMarkaById(Long id);

    MarkaEntity addMarka(MarkaEntity markaEntity);

    MarkaEntity updateMarka(MarkaEntity updatedMarka);

    boolean deleteMarkaById(Long id);
}

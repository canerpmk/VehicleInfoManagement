package com.example.sahibinden.service;

import com.example.sahibinden.model.Ozellik;

import java.util.List;

public interface OzellikService {
    List<Ozellik> getAllOzellik();

    Ozellik getOzellikById(Long id);

    Ozellik addOzellik(Ozellik ozellik);

    Ozellik updateOzellik(Ozellik updatedOzellik);

    boolean deleteOzellikById(Long id);


}

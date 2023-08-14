package com.example.sahibinden.service;

import com.example.sahibinden.model.Ozellik;
import com.example.sahibinden.model.Paket;

import java.util.List;

public interface PaketService {


    Paket getPaketById(Long id);

    Paket addPaket(Paket paket);

    Paket updatePaket(Paket updatedPaket);

    boolean deletePaketById(Long id);
}

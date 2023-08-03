package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.entity.PaketEntity;
import com.example.sahibinden.repository.PaketRepository;
import com.example.sahibinden.service.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaketServiceImpl {
    private final PaketRepository paketRepository;

    public PaketEntity getPaketById(Long id) {
        return paketRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id));
    }

    public List<PaketEntity> getAllPaket() {
        return paketRepository.findAll();
    }

    public PaketEntity addPaket(PaketEntity paket) {
        return paketRepository.save(paket);
    }

    public PaketEntity updatePaket(PaketEntity updatedPaket) {
        if (paketRepository.existsById(updatedPaket.getId())) {
            return paketRepository.save(updatedPaket);
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + updatedPaket.getId());
    }

    public boolean deletePaketById(Long id) {
        if (paketRepository.existsById(id)) {
            paketRepository.deleteById(id);
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id);
    }
}

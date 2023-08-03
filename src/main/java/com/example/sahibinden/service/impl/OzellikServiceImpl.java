package com.example.sahibinden.service.impl;
import com.example.sahibinden.model.entity.OzellikEntity;
import com.example.sahibinden.repository.OzellikRepository;
import com.example.sahibinden.service.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OzellikServiceImpl {
    private final OzellikRepository ozellikRepository;

    public OzellikEntity getOzellikById(Long id) {
        return ozellikRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id));
    }

    public List<OzellikEntity> getAllOzellik() {
        return ozellikRepository.findAll();
    }

    public OzellikEntity addOzellik(OzellikEntity ozellik) {
        return ozellikRepository.save(ozellik);
    }

    public OzellikEntity updateOzellik(OzellikEntity updatedOzellik) {
        if (ozellikRepository.existsById(updatedOzellik.getId())) {
            return ozellikRepository.save(updatedOzellik);
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + updatedOzellik.getId());
    }

    public boolean deleteOzellikById(Long id) {
        if (ozellikRepository.existsById(id)) {
            ozellikRepository.deleteById(id);
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id);
    }
}

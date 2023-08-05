package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.repository.MarkaRepository;
import com.example.sahibinden.exception.model.CustomException;
import com.example.sahibinden.service.MarkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkaServiceImpl implements MarkaService {
    private final MarkaRepository markaRepository;

    public MarkaEntity getMarkaById(Long id) {
        return markaRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id));
    }

    public List<MarkaEntity> getAllMarka() {
        return markaRepository.findAll();
    }

    public MarkaEntity addMarka(MarkaEntity markaEntity) {
        return markaRepository.save(markaEntity);
    }

    public MarkaEntity updateMarka(MarkaEntity updatedMarka) {
        if (markaRepository.existsById(updatedMarka.getId())) {
            return markaRepository.save(updatedMarka);
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + updatedMarka.getId());
    }

    public boolean deleteMarkaById(Long id) {
        if (markaRepository.existsById(id)) {
            markaRepository.deleteById(id);
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id);
    }
}

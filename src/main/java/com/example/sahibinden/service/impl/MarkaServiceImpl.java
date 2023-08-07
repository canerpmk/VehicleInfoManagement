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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarkaServiceImpl implements MarkaService {
    private final MarkaRepository markaRepository;


    public Marka getMarkaById(Long id) {
        MarkaEntity markaEntity = markaRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id));
        return Marka.fromEntity(markaEntity);
    }


    public List<Marka> getAllMarka() {
        List<MarkaEntity> markaEntities = markaRepository.findAll();
        return markaEntities.stream()
                .map(Marka::fromEntity)
                .collect(Collectors.toList());
    }


    public Marka addMarka(Marka marka) {
        MarkaEntity markaEntity = MarkaEntity.fromModel(marka);
        MarkaEntity addedMarkaEntity = markaRepository.save(markaEntity);
        return Marka.fromEntity(addedMarkaEntity);
    }


    public Marka updateMarka(Marka marka) {
        if (markaRepository.existsById(marka.getId())) {
            MarkaEntity updatedMarkaEntity = markaRepository.save(MarkaEntity.fromModel(marka));
            return Marka.fromEntity(updatedMarkaEntity);
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + marka.getId());
    }


    public boolean deleteMarkaById(Long id) {
        if (markaRepository.existsById(id)) {
            markaRepository.deleteById(id);
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id);
    }
}
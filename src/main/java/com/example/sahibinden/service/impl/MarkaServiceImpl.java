package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.repository.MarkaRepository;
import com.example.sahibinden.service.MarkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarkaServiceImpl implements MarkaService {
    private final MarkaRepository markaRepository;


    public Marka getMarkaById(Long id) {
        MarkaEntity markaEntity = markaRepository.findById(id).orElseThrow();
        return Marka.fromEntity(markaEntity);
    }

    public Marka getMarkaByShortName(String shortName) {
        MarkaEntity markaEntity = markaRepository.findMarkaEntityByShortName(shortName).orElseThrow();
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

    public List<Marka> addMarkas(List<Marka> markaList) {
        List<MarkaEntity> markaEntityList = markaList.stream().map(MarkaEntity::fromModel).toList();
        List<MarkaEntity> addedMarkaEntityList = markaRepository.saveAll(markaEntityList);
        return addedMarkaEntityList.stream().map(Marka::fromEntity).toList();
    }


    public Marka updateMarka(Marka marka) {
        if (markaRepository.existsById(marka.getId())) {
            MarkaEntity updatedMarkaEntity = markaRepository.save(MarkaEntity.fromModel(marka));
            return Marka.fromEntity(updatedMarkaEntity);
        }
        return null;
    }

    public boolean deleteMarkaById(Long id) {
        markaRepository.deleteById(id);
        return !markaRepository.existsById(id);
    }


}
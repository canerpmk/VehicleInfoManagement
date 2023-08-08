package com.example.sahibinden.service.impl;
import com.example.sahibinden.model.Ozellik;
import com.example.sahibinden.model.entity.OzellikEntity;
import com.example.sahibinden.repository.OzellikRepository;
import com.example.sahibinden.exception.model.CustomException;
import com.example.sahibinden.service.OzellikService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OzellikServiceImpl implements OzellikService {
    private final OzellikRepository ozellikRepository;

    public Ozellik getOzellikById(Long id) {
        OzellikEntity ozellikEntity = ozellikRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id));
        return Ozellik.fromEntity(ozellikEntity);
    }

    public List<Ozellik> getAllOzellik() {
        List<OzellikEntity> ozellikEntities = ozellikRepository.findAll();
        return ozellikEntities.stream()
                .map(Ozellik::fromEntity)
                .collect(Collectors.toList());
    }

    public Ozellik addOzellik(Ozellik ozellik) {
        OzellikEntity ozellikEntity = OzellikEntity.fromModel(ozellik);
        OzellikEntity addedOzellikEntity = ozellikRepository.save(ozellikEntity);
        return Ozellik.fromEntity(addedOzellikEntity);
    }

    public Ozellik updateOzellik(Ozellik ozellik) {
        if (ozellikRepository.existsById(ozellik.getId())) {
            OzellikEntity updatedOzellikEntity = ozellikRepository.save(OzellikEntity.fromModel(ozellik));
            return Ozellik.fromEntity(updatedOzellikEntity);
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + ozellik.getId());
    }

    public boolean deleteOzellikById(Long id) {
        if (ozellikRepository.existsById(id)) {
            ozellikRepository.deleteById(id);
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id);
    }
}

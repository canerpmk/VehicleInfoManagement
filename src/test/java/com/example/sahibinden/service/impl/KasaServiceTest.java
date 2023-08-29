package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.Model;
import com.example.sahibinden.model.entity.KasaEntity;
import com.example.sahibinden.model.entity.ModelEntity;
import com.example.sahibinden.repository.KasaRepository;
import com.example.sahibinden.repository.ModelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KasaServiceTest {
    @Mock
    private KasaRepository kasaRepository;

    @InjectMocks
    private KasaServiceImpl kasaService;

    @Mock
    private ModelRepository modelRepository;

    @Test
    public void getKasaById() {
        Long kasaId = 1L;
        KasaEntity kasaEntity = new KasaEntity();
        kasaEntity.setId(kasaId);
        kasaEntity.setShortName("sedan");

        when(kasaRepository.findById(kasaId)).thenReturn(Optional.of(kasaEntity));

        Kasa kasa = kasaService.getKasaById(kasaId);

        assertEquals(kasaId, kasa.getId());
        assertEquals("sedan", kasa.getShortName());

    }

    @Test
    public void getKasaByShortName() {
        String shortName = "sedan";
        KasaEntity kasaEntity = new KasaEntity();
        kasaEntity.setId(1L);
        kasaEntity.setShortName(shortName);

        when(kasaRepository.findKasaEntityByShortName(shortName)).thenReturn(Optional.of(kasaEntity));

        Kasa kasa = kasaService.getKasaByShortName(shortName);

        assertEquals(1L, kasa.getId());
        assertEquals(shortName, kasa.getShortName());

    }

    @Test
    public void allKasa() {

        List<KasaEntity> kasaEntityList = new ArrayList<>();
        KasaEntity kasaEntity1 = new KasaEntity();
        kasaEntity1.setId(1L);

        KasaEntity kasaEntity2 = new KasaEntity();
        kasaEntity2.setId(2L);

        kasaEntityList.add(kasaEntity1);
        kasaEntityList.add(kasaEntity2);

        when(kasaRepository.findAll()).thenReturn(kasaEntityList);

        List<Kasa> kasaList = kasaService.getAllKasa();

        assertEquals(2, kasaList.size());

        Kasa kasa1 = kasaList.get(0);
        assertEquals(1L, kasa1.getId());

        Kasa kasa2 = kasaList.get(1);
        assertEquals(2L, kasa2.getId());

    }

    @Test
    public void addKasa() {
        Long modelId = 1L;
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setId(modelId);

        Kasa kasaToAdd = Kasa.builder()
                .model(Model.builder().id(modelId).build())
                .build();

        KasaEntity addedKasaEntity = KasaEntity.builder()
                .id(1L)
                .model(modelEntity)
                .build();

        when(modelRepository.findById(modelId)).thenReturn(Optional.of(modelEntity));
        when(kasaRepository.save(any(KasaEntity.class))).thenReturn(addedKasaEntity);


        Kasa addedKasa = kasaService.addKasa(kasaToAdd);


        assertEquals(addedKasaEntity.getId(), addedKasa.getId());
        assertEquals(modelId, addedKasa.getModel().getId());
    }


    @Test
    void updateKasa() {
        Long kasaId = 1L;
        Kasa kasaToUpdate = Kasa.builder().id(kasaId).build();
        KasaEntity updatedKasaEntity = KasaEntity.builder().id(kasaId).build();

        when(kasaRepository.existsById(kasaId)).thenReturn(true);
        when(kasaRepository.save(any(KasaEntity.class))).thenReturn(updatedKasaEntity);


        Kasa updatedKasa = kasaService.updateKasa(kasaToUpdate);


        assertNotNull(updatedKasa);
        assertEquals(kasaId, updatedKasa.getId());

    }

    @Test
    void deleteKasa() {
        Long kasaId = 1L;

        when(kasaRepository.existsById(kasaId)).thenReturn(false);
        doNothing().when(kasaRepository).deleteById(kasaId);

        boolean isDeleted = kasaService.deleteKasaById(kasaId);


        assertTrue(isDeleted);

        verify(kasaRepository).existsById(kasaId);
        verify(kasaRepository).deleteById(kasaId);
    }
}


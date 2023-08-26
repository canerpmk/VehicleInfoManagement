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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class KasaServiceTest {
    @Mock(lenient = true)
    private KasaRepository kasaRepository;

    @InjectMocks
    private KasaServiceImpl kasaService;

    @Mock(lenient = true)
    private ModelRepository modelRepository;

    @Test
    public void testGetKasaById() {
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
    public void testGetKasaByShortName() {
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
    public void testGetAllKasa() {
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
    public void testAddKasa() {
        // Test verileri oluşturma
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setId(1L);
        // Diğer gerekli alanları da ayarlayabilirsiniz...

        Kasa kasa = new Kasa();
        kasa.setId(1L);
        kasa.setModel(new Model());
        // Diğer gerekli alanları da ayarlayabilirsiniz...

        KasaEntity kasaEntity = new KasaEntity();
        kasaEntity.setId(1L);
        // Diğer gerekli alanları da ayarlayabilirsiniz...

        // Mock davranışlarını ayarlama
        when(modelRepository.findById(1L)).thenReturn(java.util.Optional.of(modelEntity));
        when(kasaRepository.save(any(KasaEntity.class))).thenReturn(kasaEntity);

        // Metodu çağırma
        Kasa addedKasa = kasaService.addKasa(kasa);

        // Sonuçları kontrol etme
        assertEquals(1L, addedKasa.getId());
        // Diğer alanları kontrol edebilirsiniz...

        verify(modelRepository, times(1)).findById(1L);
        verify(kasaRepository, times(1)).save(any(KasaEntity.class));
    }



}

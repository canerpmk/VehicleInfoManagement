package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.Model;
import com.example.sahibinden.model.entity.KasaEntity;
import com.example.sahibinden.model.entity.ModelEntity;
import com.example.sahibinden.repository.KasaRepository;
import com.example.sahibinden.repository.ModelRepository;
import com.example.sahibinden.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        KasaEntity kasaEntity = TestUtils.kasaEntity(1L, "sedan");


        when(kasaRepository.findById(kasaEntity.getId())).thenReturn(Optional.of(kasaEntity));

        Kasa kasa = kasaService.getKasaById(kasaEntity.getId());

        assertEquals(kasaEntity.getId(), kasa.getId());
        assertEquals(kasaEntity.getShortName(), kasa.getShortName());

    }

    @Test
    public void getKasaByShortName() {

        KasaEntity kasaEntity = TestUtils.kasaEntity(1L, "shortname");


        when(kasaRepository.findKasaEntityByShortName(kasaEntity.getShortName())).thenReturn(Optional.of(kasaEntity));

        Kasa kasa = kasaService.getKasaByShortName(kasaEntity.getShortName());


        assertEquals(kasaEntity.getId(), kasa.getId());
        assertEquals(kasaEntity.getShortName(), kasa.getShortName());

    }

    @Test
    public void allKasa() {

        List<KasaEntity> kasaEntityList = List.of(TestUtils.kasaEntity(), TestUtils.kasaEntity());


        when(kasaRepository.findAll()).thenReturn(kasaEntityList);

        List<Kasa> kasaList = kasaService.getAllKasa();

        assertEquals(kasaEntityList.size(), kasaList.size());

        Kasa kasa1 = kasaList.get(0);
        assertEquals(kasaEntityList.get(0).getId(), kasa1.getId());

        Kasa kasa2 = kasaList.get(1);
        assertEquals(kasaEntityList.get(1).getId(), kasa2.getId());

    }

    @Test
    public void addKasa() {

        ModelEntity modelEntity = TestUtils.modelEntity();


        Kasa kasaToAdd = Kasa.builder()
                .model(Model.builder().id(modelEntity.getId()).build())
                .build();

        KasaEntity addedKasaEntity = KasaEntity.builder()
                .id(1L)
                .model(modelEntity)
                .build();

        when(modelRepository.findById(modelEntity.getId())).thenReturn(Optional.of(modelEntity));
        when(kasaRepository.save(any(KasaEntity.class))).thenReturn(addedKasaEntity);


        Kasa addedKasa = kasaService.addKasa(kasaToAdd);


        assertEquals(addedKasaEntity.getId(), addedKasa.getId());
        assertEquals(modelEntity.getId(), addedKasa.getModel().getId());
    }

    @Test
    public void testAddKasas() {
        List<Kasa> kasaList = List.of(TestUtils.kasaBuilder(), TestUtils.kasaBuilder());


        KasaEntity savedKasaEntity = TestUtils.kasaEntity();
        when(kasaRepository.save(any(KasaEntity.class))).thenReturn(savedKasaEntity);

        List<Kasa> result = kasaService.addKasas(kasaList);

        assertNotNull(result);
        assertEquals(kasaList.size(), result.size());
    }


    @Test
    void updateKasa() {

        Kasa kasaToUpdate = TestUtils.kasaBuilder();
        KasaEntity updatedKasaEntity = TestUtils.kasaEntity(kasaToUpdate.getId());

        when(kasaRepository.existsById(kasaToUpdate.getId())).thenReturn(true);
        when(kasaRepository.save(any(KasaEntity.class))).thenReturn(updatedKasaEntity);


        Kasa updatedKasa = kasaService.updateKasa(kasaToUpdate);


        assertNotNull(updatedKasa);
        assertEquals(kasaToUpdate.getId(), updatedKasa.getId());

    }

    @Test
    void deleteKasa() {
        Long kasaId = TestUtils.randomId();

        when(kasaRepository.existsById(kasaId)).thenReturn(false);
        doNothing().when(kasaRepository).deleteById(kasaId);

        boolean isDeleted = kasaService.deleteKasaById(kasaId);


        assertTrue(isDeleted);

        verify(kasaRepository).existsById(kasaId);
        verify(kasaRepository).deleteById(kasaId);
    }
}


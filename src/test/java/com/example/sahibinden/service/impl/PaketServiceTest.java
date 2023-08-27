package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Paket;
import com.example.sahibinden.model.entity.PaketEntity;
import com.example.sahibinden.repository.PaketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class PaketServiceTest {
    @Mock
    private PaketRepository paketRepository;

    @InjectMocks
    private PaketServiceImpl paketService;

    @Test
    void testGetPaketById() {
        Long paketId = 1L;
        PaketEntity mockedPaketEntity = new PaketEntity();

        when(paketRepository.findById(paketId)).thenReturn(java.util.Optional.of(mockedPaketEntity));

        Paket returnedPaket = paketService.getPaketById(paketId);

        assertNotNull(returnedPaket);
        assertEquals(mockedPaketEntity.getId(), returnedPaket.getId());
        verify(paketRepository).findById(paketId);
        verifyNoMoreInteractions(paketRepository);
    }

    @Test
    void testGetAllPaket() {
        List<PaketEntity> mockedPaketEntities = new ArrayList<>();


        when(paketRepository.findAll()).thenReturn(mockedPaketEntities);

        List<Paket> returnedPaketList = paketService.getAllPaket();

        assertNotNull(returnedPaketList);
        assertEquals(mockedPaketEntities.size(), returnedPaketList.size());
        verify(paketRepository).findAll();
        verifyNoMoreInteractions(paketRepository);
    }

    @Test
    void testAddPaket() {
        Paket inputPaket = new Paket();

        PaketEntity mockedPaketEntity = new PaketEntity();

        when(paketRepository.save(any())).thenReturn(mockedPaketEntity);

        Paket returnedPaket = paketService.addPaket(inputPaket);

        assertNotNull(returnedPaket);
        assertEquals(mockedPaketEntity.getId(), returnedPaket.getId());
        verify(paketRepository).save(any());
        verifyNoMoreInteractions(paketRepository);
    }

    @Test
    void testUpdatePaket() {
        Paket inputPaket = new Paket();

        PaketEntity existingPaketEntity = new PaketEntity();
        when(paketRepository.existsById(inputPaket.getId())).thenReturn(true);
        when(paketRepository.save(any())).thenReturn(existingPaketEntity);

        Paket updatedPaket = paketService.updatePaket(inputPaket);

        assertNotNull(updatedPaket);
        assertEquals(existingPaketEntity.getId(), updatedPaket.getId());
        verify(paketRepository).existsById(inputPaket.getId());
        verify(paketRepository).save(any());
        verifyNoMoreInteractions(paketRepository);
    }

    @Test
    void testDeletePaketById() {
        Long paketId = 1L;
        when(paketRepository.existsById(paketId)).thenReturn(false);

        boolean isDeleted = paketService.deletePaketById(paketId);

        assertTrue(isDeleted);
        verify(paketRepository).deleteById(paketId);
        verify(paketRepository).existsById(paketId);
        verifyNoMoreInteractions(paketRepository);
    }
}

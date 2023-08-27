package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Ozellik;
import com.example.sahibinden.model.entity.OzellikEntity;
import com.example.sahibinden.repository.OzellikRepository;
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
public class OzellikServiceTest {
    @Mock
    private OzellikRepository ozellikRepository;

    @InjectMocks
    private OzellikServiceImpl ozellikService;

    @Test
    void testGetOzellikById() {
        Long ozellikId = 1L;
        OzellikEntity mockedOzellikEntity = new OzellikEntity();
        // Set properties for mockedOzellikEntity

        when(ozellikRepository.findById(ozellikId)).thenReturn(java.util.Optional.of(mockedOzellikEntity));

        Ozellik returnedOzellik = ozellikService.getOzellikById(ozellikId);

        assertNotNull(returnedOzellik);
        assertEquals(mockedOzellikEntity.getId(), returnedOzellik.getId());
        verify(ozellikRepository).findById(ozellikId);
        verifyNoMoreInteractions(ozellikRepository);
    }

    @Test
    void testGetAllOzellik() {
        List<OzellikEntity> mockedOzellikEntities = new ArrayList<>();
        // Create and add OzellikEntity instances to mockedOzellikEntities
        // ...

        when(ozellikRepository.findAll()).thenReturn(mockedOzellikEntities);

        List<Ozellik> returnedOzellikList = ozellikService.getAllOzellik();

        assertNotNull(returnedOzellikList);
        assertEquals(mockedOzellikEntities.size(), returnedOzellikList.size());
        verify(ozellikRepository).findAll();
        verifyNoMoreInteractions(ozellikRepository);
    }

    @Test
    void testAddOzellik() {
        Ozellik inputOzellik = new Ozellik();
        // Set properties for inputOzellik

        OzellikEntity mockedOzellikEntity = new OzellikEntity();
        // Set properties for mockedOzellikEntity
        when(ozellikRepository.save(any())).thenReturn(mockedOzellikEntity);

        Ozellik returnedOzellik = ozellikService.addOzellik(inputOzellik);

        assertNotNull(returnedOzellik);
        assertEquals(mockedOzellikEntity.getId(), returnedOzellik.getId());
        verify(ozellikRepository).save(any());
        verifyNoMoreInteractions(ozellikRepository);
    }

    @Test
    void testUpdateOzellik() {
        Ozellik inputOzellik = new Ozellik();
        // Set properties for inputOzellik

        OzellikEntity existingOzellikEntity = new OzellikEntity();
        // Set properties for existingOzellikEntity
        when(ozellikRepository.existsById(inputOzellik.getId())).thenReturn(true);
        when(ozellikRepository.save(any())).thenReturn(existingOzellikEntity);

        Ozellik updatedOzellik = ozellikService.updateOzellik(inputOzellik);

        assertNotNull(updatedOzellik);
        assertEquals(existingOzellikEntity.getId(), updatedOzellik.getId());
        verify(ozellikRepository).existsById(inputOzellik.getId());
        verify(ozellikRepository).save(any());
        verifyNoMoreInteractions(ozellikRepository);
    }

    @Test
    void testDeleteOzellikById() {
        Long ozellikId = 1L;
        when(ozellikRepository.existsById(ozellikId)).thenReturn(false);

        boolean isDeleted = ozellikService.deleteOzellikById(ozellikId);

        assertTrue(isDeleted);
        verify(ozellikRepository).deleteById(ozellikId);
        verify(ozellikRepository).existsById(ozellikId);
        verifyNoMoreInteractions(ozellikRepository);
    }
}

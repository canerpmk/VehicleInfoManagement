package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Ozellik;
import com.example.sahibinden.model.entity.OzellikEntity;
import com.example.sahibinden.repository.OzellikRepository;
import com.example.sahibinden.utils.TestUtils;
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

        OzellikEntity mockedOzellikEntity = TestUtils.ozellikEntity();


        when(ozellikRepository.findById(mockedOzellikEntity.getId())).thenReturn(java.util.Optional.of(mockedOzellikEntity));

        Ozellik returnedOzellik = ozellikService.getOzellikById(mockedOzellikEntity.getId());

        assertNotNull(returnedOzellik);
        assertEquals(mockedOzellikEntity.getId(), returnedOzellik.getId());
        verify(ozellikRepository).findById(mockedOzellikEntity.getId());
        verifyNoMoreInteractions(ozellikRepository);
    }

    @Test
    void testGetAllOzellik() {
        List<OzellikEntity> mockedOzellikEntities = new ArrayList<>();


        when(ozellikRepository.findAll()).thenReturn(mockedOzellikEntities);

        List<Ozellik> returnedOzellikList = ozellikService.getAllOzellik();

        assertNotNull(returnedOzellikList);
        assertEquals(mockedOzellikEntities.size(), returnedOzellikList.size());
        verify(ozellikRepository).findAll();
        verifyNoMoreInteractions(ozellikRepository);
    }

    @Test
    void testAddOzellik() {
        Ozellik inputOzellik = TestUtils.ozellikBuilder();


        OzellikEntity mockedOzellikEntity = TestUtils.ozellikEntity();

        when(ozellikRepository.save(any())).thenReturn(mockedOzellikEntity);

        Ozellik returnedOzellik = ozellikService.addOzellik(inputOzellik);

        assertNotNull(returnedOzellik);
        assertEquals(mockedOzellikEntity.getId(), returnedOzellik.getId());
        verify(ozellikRepository).save(any());
        verifyNoMoreInteractions(ozellikRepository);
    }

    @Test
    void testUpdateOzellik() {
        Ozellik inputOzellik = TestUtils.ozellikBuilder();

        OzellikEntity existingOzellikEntity = TestUtils.ozellikEntity();
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
        Long ozellikId = TestUtils.randomId();
        when(ozellikRepository.existsById(ozellikId)).thenReturn(false);

        boolean isDeleted = ozellikService.deleteOzellikById(ozellikId);

        assertTrue(isDeleted);
        verify(ozellikRepository).deleteById(ozellikId);
        verify(ozellikRepository).existsById(ozellikId);
        verifyNoMoreInteractions(ozellikRepository);
    }
}

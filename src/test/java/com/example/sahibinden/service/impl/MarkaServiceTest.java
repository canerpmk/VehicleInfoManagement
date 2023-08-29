package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.repository.MarkaRepository;
import com.example.sahibinden.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MarkaServiceTest {
    @Mock
    private MarkaRepository markaRepository;

    @InjectMocks
    private MarkaServiceImpl markaService;

    @Test
    public void getMarkaById() {
        MarkaEntity markaEntity = TestUtils.markaEntity();


        when(markaRepository.findById(markaEntity.getId())).thenReturn(Optional.of(markaEntity));

        Marka marka = markaService.getMarkaById(markaEntity.getId());


        assertEquals(markaEntity.getId(), marka.getId());

    }

    @Test
    void getAllMarka() {

        List<MarkaEntity> markaEntityList = List.of(TestUtils.markaEntity(), TestUtils.markaEntity());

        //when
        when(markaRepository.findAll()).thenReturn(markaEntityList);


        List<Marka> markaList = markaService.getAllMarka();


        assertEquals(markaEntityList.size(), markaList.size());

        Marka marka1 = markaList.get(0);
        assertEquals(markaEntityList.get(0).getId(), marka1.getId());

        Marka marka2 = markaList.get(1);
        assertEquals(markaEntityList.get(1).getId(), marka2.getId());
    }

    @Test
    void addMarka() {

        Marka markaToAdd = TestUtils.markaBuilder();


        MarkaEntity addedMarkaEntity = TestUtils.markaEntity();


        when(markaRepository.save(any(MarkaEntity.class))).thenReturn(addedMarkaEntity);


        Marka addedMarka = markaService.addMarka(markaToAdd);


        assertNotNull(addedMarka);
        assertEquals(addedMarkaEntity.getId(), addedMarka.getId());
    }

    @Test
    void updateMarka() {

        Marka markaToUpdate = TestUtils.marka(1L, "Updated Marka");

        MarkaEntity updatedMarkaEntity = TestUtils.markaEntity(TestUtils.markaBuilder().getId(), markaToUpdate.getName());

        when(markaRepository.existsById(markaToUpdate.getId())).thenReturn(true);
        when(markaRepository.save(any(MarkaEntity.class))).thenReturn(updatedMarkaEntity);

        Marka updatedMarka = markaService.updateMarka(markaToUpdate);

        assertEquals(updatedMarkaEntity.getId(), updatedMarka.getId());
        assertEquals(markaToUpdate.getName(), updatedMarka.getName());
        assertEquals(markaToUpdate.getName(), updatedMarkaEntity.getName());

        verify(markaRepository).existsById(markaToUpdate.getId());
        verify(markaRepository).save(any(MarkaEntity.class));

    }

    @Test
    void testAddMarkas() {
        List<Marka> inputMarkaList = new ArrayList<>();


        List<MarkaEntity> mockedMarkaEntityList = new ArrayList<>();


        when(markaRepository.saveAll(any())).thenReturn(mockedMarkaEntityList);

        List<Marka> returnedMarkaList = markaService.addMarkas(inputMarkaList);

        assertNotNull(returnedMarkaList);
        assertEquals(mockedMarkaEntityList.size(), returnedMarkaList.size());
        verify(markaRepository).saveAll(any());
        verifyNoMoreInteractions(markaRepository);
    }

    @Test
    void deleteMarka() {
        Long markaId = TestUtils.randomId();

        //TODO berkay abiye saygı duy

        when(markaRepository.existsById(markaId)).thenReturn(false);
        doNothing().when(markaRepository).deleteById(markaId);

        boolean isDeleted = markaService.deleteMarkaById(markaId);

        assertTrue(isDeleted);

        verify(markaRepository).existsById(markaId);
        verify(markaRepository).deleteById(markaId);

    }

    @Test
    void getByShortName() {
        MarkaEntity markaEntity = TestUtils.markaEntityShortname(1L, "x");


        when(markaRepository.findMarkaEntityByShortName(markaEntity.getShortName())).thenReturn(Optional.of(markaEntity));

        Marka marka = markaService.getMarkaByShortName(markaEntity.getShortName());


        assertEquals(markaEntity.getId(), marka.getId());
        assertEquals(markaEntity.getShortName(), marka.getShortName());
    }
}

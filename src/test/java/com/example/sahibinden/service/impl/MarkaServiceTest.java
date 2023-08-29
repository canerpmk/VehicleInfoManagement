package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.repository.MarkaRepository;
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
        Long markaId = 1L;
        MarkaEntity markaEntity = new MarkaEntity();
        markaEntity.setId(markaId);

        when(markaRepository.findById(markaId)).thenReturn(Optional.of(markaEntity));

        Marka marka = markaService.getMarkaById(markaId);


        assertEquals(markaId, marka.getId());

    }

    @Test
    void getAllMarka() {

        List<MarkaEntity> markaEntityList = new ArrayList<>();
        MarkaEntity markaEntity1 = new MarkaEntity();
        markaEntity1.setId(1L);

        MarkaEntity markaEntity2 = new MarkaEntity();
        markaEntity2.setId(2L);

        markaEntityList.add(markaEntity1);
        markaEntityList.add(markaEntity2);

        when(markaRepository.findAll()).thenReturn(markaEntityList);


        List<Marka> markaList = markaService.getAllMarka();


        assertEquals(2, markaList.size());

        Marka marka1 = markaList.get(0);
        assertEquals(1L, marka1.getId());

        Marka marka2 = markaList.get(1);
        assertEquals(2L, marka2.getId());
    }

    @Test
    void addMarka() {
        Long markaId = 1L;
        Marka markaToAdd = Marka.builder().build();

        MarkaEntity addedMarkaEntity = MarkaEntity.builder()
                .id(markaId)
                .build();

        when(markaRepository.save(any(MarkaEntity.class))).thenReturn(addedMarkaEntity);


        Marka addedMarka = markaService.addMarka(markaToAdd);


        assertNotNull(addedMarka);
        assertEquals(markaId, addedMarka.getId());
    }

    @Test
    void updateMarka() {
        Long markaId = 1L;
        Marka markaToUpdate = Marka.builder()
                .id(markaId)
                .name("Updated Marka")
                .build();

        MarkaEntity updatedMarkaEntity = MarkaEntity.builder()
                .id(markaId)
                .name("Updated Marka")
                .build();

        when(markaRepository.existsById(markaId)).thenReturn(true);
        when(markaRepository.save(any(MarkaEntity.class))).thenReturn(updatedMarkaEntity);

        Marka updatedMarka = markaService.updateMarka(markaToUpdate);

        assertEquals(markaId, updatedMarka.getId());
        assertEquals("Updated Marka", updatedMarka.getName());

        verify(markaRepository).existsById(markaId);
        verify(markaRepository).save(any(MarkaEntity.class));

    }

    @Test
    void addMarkas() {
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
        Long markaId = 1L;

        when(markaRepository.existsById(markaId)).thenReturn(false);
        doNothing().when(markaRepository).deleteById(markaId);

        boolean isDeleted = markaService.deleteMarkaById(markaId);

        assertTrue(isDeleted);

        verify(markaRepository).existsById(markaId);
        verify(markaRepository).deleteById(markaId);

    }

    @Test
    void getByShortName() {
        String shortName = "sedan";
        MarkaEntity markaEntity = new MarkaEntity();
        markaEntity.setId(1L);
        markaEntity.setShortName(shortName);

        when(markaRepository.findMarkaEntityByShortName(shortName)).thenReturn(Optional.of(markaEntity));

        Marka marka = markaService.getMarkaByShortName(shortName);

        assertEquals(1L, marka.getId());
        assertEquals(shortName, marka.getShortName());
    }
}

package com.example.sahibinden.controller;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.dto.MarkaRequest;
import com.example.sahibinden.model.dto.MarkaResponse;
import com.example.sahibinden.service.KasaService;
import com.example.sahibinden.service.MarkaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MarkaControllerTest {
    @Mock
    private MarkaService markaService;

    @InjectMocks
    private MarkaController markaController;
    @Test
    void getMarkaById() {
        Long markaId = 1L;
        Marka mockMarka = new Marka();
        when(markaService.getMarkaById(markaId)).thenReturn(mockMarka);

        ResponseEntity<MarkaResponse> responseEntity = markaController.getMarkaById(markaId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(markaService, times(1)).getMarkaById(markaId);
    }
    @Test
    void getAllMarkalar() {
        List<Marka> mockMarkalar = Arrays.asList(new Marka(), new Marka());
        when(markaService.getAllMarka()).thenReturn(mockMarkalar);

        ResponseEntity<List<MarkaResponse>> responseEntity = markaController.getAllMarkalar();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockMarkalar.size(), responseEntity.getBody().size());

        verify(markaService, times(1)).getAllMarka();
    }
    @Test
    void addMarka() {
        MarkaRequest mockMarkaRequest = new MarkaRequest();
        Marka mockAddedMarka = new Marka();
        when(markaService.addMarka(any(Marka.class))).thenReturn(mockAddedMarka);

        ResponseEntity<MarkaResponse> responseEntity = markaController.addMarka(mockMarkaRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(markaService, times(1)).addMarka(any(Marka.class));
    }
    @Test
    void updateMarka() {
        Long markaId = 1L;
        MarkaRequest mockMarkaRequest = new MarkaRequest();
        Marka mockUpdatedMarka = new Marka();
        when(markaService.updateMarka(any(Marka.class))).thenReturn(mockUpdatedMarka);

        ResponseEntity<MarkaResponse> responseEntity = markaController.updateMarka(markaId, mockMarkaRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(markaService, times(1)).updateMarka(any(Marka.class));
    }
    @Test
    void deleteMarka() {
        Long markaId = 1L;

        ResponseEntity<Void> responseEntity = markaController.deleteMarka(markaId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        verify(markaService, times(1)).deleteMarkaById(markaId);
    }
}

package com.example.sahibinden.controller;

import com.example.sahibinden.model.Paket;
import com.example.sahibinden.model.dto.PaketRequest;
import com.example.sahibinden.model.dto.PaketResponse;
import com.example.sahibinden.service.impl.PaketServiceImpl;
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
public class PaketControllerTest {
    @Mock
    private PaketServiceImpl paketService;

    @InjectMocks
    private PaketController paketController;

    @Test
    void getPaketById() {
        Long paketId = 1L;
        Paket mockPaket = new Paket();
        when(paketService.getPaketById(paketId)).thenReturn(mockPaket);

        ResponseEntity<PaketResponse> responseEntity = paketController.getPaketById(paketId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(paketService, times(1)).getPaketById(paketId);
    }

    @Test
    void getAllPaketler() {
        List<Paket> mockPaketler = Arrays.asList(new Paket(), new Paket());
        when(paketService.getAllPaket()).thenReturn(mockPaketler);

        ResponseEntity<List<PaketResponse>> responseEntity = paketController.getAllPaketler();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockPaketler.size(), responseEntity.getBody().size());

        verify(paketService, times(1)).getAllPaket();
    }

    @Test
    void addPaket() {
        PaketRequest mockPaketRequest = new PaketRequest();
        Paket mockAddedPaket = new Paket();
        when(paketService.addPaket(any(Paket.class))).thenReturn(mockAddedPaket);

        ResponseEntity<PaketResponse> responseEntity = paketController.addPaket(mockPaketRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(paketService, times(1)).addPaket(any(Paket.class));
    }

    @Test
    void updatePaket() {
        Long paketId = 1L;
        PaketRequest mockPaketRequest = new PaketRequest();
        Paket mockUpdatedPaket = new Paket();
        when(paketService.updatePaket(any(Paket.class))).thenReturn(mockUpdatedPaket);

        ResponseEntity<PaketResponse> responseEntity = paketController.updatePaket(paketId, mockPaketRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(paketService, times(1)).updatePaket(any(Paket.class));
    }

    @Test
    void deletePaket() {
        Long paketId = 1L;

        ResponseEntity<Void> responseEntity = paketController.deletePaket(paketId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        verify(paketService, times(1)).deletePaketById(paketId);
    }
}

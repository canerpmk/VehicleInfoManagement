package com.example.sahibinden.controller;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.dto.KasaRequest;
import com.example.sahibinden.model.dto.KasaResponse;
import com.example.sahibinden.service.KasaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KasaControllerTest {
    @Mock
    private KasaService kasaService;

    @InjectMocks
    private KasaController kasaController;

    @Test
    void testGetKasaById() {
        Long kasaId = 1L;
        Kasa mockKasa = new Kasa();
        when(kasaService.getKasaById(kasaId)).thenReturn(mockKasa);

        // Call the method
        ResponseEntity<KasaResponse> responseEntity = kasaController.getKasaById(kasaId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(kasaService, times(1)).getKasaById(kasaId);
    }

    @Test
    void getAllKasalar() {
        // Verilen
        List<KasaResponse> mockKasaResponses = new ArrayList<>();
        when(kasaService.getAllKasa()).thenReturn(new ArrayList<>());

        // Zaman
        ResponseEntity<List<KasaResponse>> responseEntity = kasaController.getAllKasalar();

        // Sonuç
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockKasaResponses, responseEntity.getBody());

        verify(kasaService, times(1)).getAllKasa();
    }


    @Test
    void testAddKasa() {
        // Prepare a mock KasaRequest
        KasaRequest mockKasaRequest = new KasaRequest();

        // Prepare the expected added Kasa
        Kasa expectedAddedKasa = new Kasa();
        when(kasaService.addKasa(any(Kasa.class))).thenReturn(expectedAddedKasa);

        // Call the method
        ResponseEntity<KasaResponse> responseEntity = kasaController.addKasa(mockKasaRequest);

        // Verify
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        // Check if the response body matches the expected added Kasa
        KasaResponse responseBody = responseEntity.getBody();
        assertEquals(expectedAddedKasa.getId(), responseBody.getId());
        // Perform other checks on the response body properties if needed

        verify(kasaService, times(1)).addKasa(any(Kasa.class));
    }

    @Test
    void testUpdateKasa() {
        Long kasaId = 1L;
        KasaRequest mockKasaRequest = new KasaRequest();
        Kasa mockUpdatedKasa = new Kasa();
        when(kasaService.updateKasa(any(Kasa.class))).thenReturn(mockUpdatedKasa);

        // Call the method
        ResponseEntity<KasaResponse> responseEntity = kasaController.updateKasa(kasaId, mockKasaRequest);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(kasaService, times(1)).updateKasa(any(Kasa.class));
    }


    @Test
    void testDeleteKasa() {
        Long kasaId = 1L;

        ResponseEntity<Void> responseEntity = kasaController.deleteKasa(kasaId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(kasaService, times(1)).deleteKasaById(kasaId);
    }
}

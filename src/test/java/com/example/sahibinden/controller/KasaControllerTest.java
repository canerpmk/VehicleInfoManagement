package com.example.sahibinden.controller;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.dto.KasaRequest;
import com.example.sahibinden.model.dto.KasaResponse;
import com.example.sahibinden.service.KasaService;
import com.example.sahibinden.utils.TestUtils;
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
        Kasa mockKasa = TestUtils.kasaBuilder();
        when(kasaService.getKasaById(mockKasa.getId())).thenReturn(mockKasa);

        ResponseEntity<KasaResponse> responseEntity = kasaController.getKasaById(mockKasa.getId());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(kasaService, times(1)).getKasaById(mockKasa.getId());
    }

    @Test
    void getAllKasalar() {
        List<KasaResponse> mockKasaResponses = new ArrayList<>();
        when(kasaService.getAllKasa()).thenReturn(new ArrayList<>());

        ResponseEntity<List<KasaResponse>> responseEntity = kasaController.getAllKasalar();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockKasaResponses, responseEntity.getBody());

        verify(kasaService, times(1)).getAllKasa();
    }


    @Test
    void testAddKasa() {
        KasaRequest mockKasaRequest = new KasaRequest();


        Kasa expectedAddedKasa = TestUtils.kasaBuilder();
        when(kasaService.addKasa(any(Kasa.class))).thenReturn(expectedAddedKasa);


        ResponseEntity<KasaResponse> responseEntity = kasaController.addKasa(mockKasaRequest);


        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());


        KasaResponse responseBody = responseEntity.getBody();
        assertEquals(expectedAddedKasa.getId(), responseBody.getId());


        verify(kasaService, times(1)).addKasa(any(Kasa.class));
    }

    @Test
    void testUpdateKasa() {

        KasaRequest mockKasaRequest = new KasaRequest();
        Kasa mockUpdatedKasa = TestUtils.kasaBuilder();
        when(kasaService.updateKasa(any(Kasa.class))).thenReturn(mockUpdatedKasa);


        ResponseEntity<KasaResponse> responseEntity = kasaController.updateKasa(mockUpdatedKasa.getId(), mockKasaRequest);


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

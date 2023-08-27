package com.example.sahibinden.controller;

import com.example.sahibinden.model.Ozellik;
import com.example.sahibinden.model.dto.OzellikRequest;
import com.example.sahibinden.model.dto.OzellikResponse;
import com.example.sahibinden.service.MotorService;
import com.example.sahibinden.service.OzellikService;
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
public class OzellikControllerTest {

    @Mock
    private OzellikService ozellikService;

    @InjectMocks
    private OzellikController ozellikController;

    @Test
    void getOzellikById() {
        Long ozellikId = 1L;
        Ozellik mockOzellik = new Ozellik();
        when(ozellikService.getOzellikById(ozellikId)).thenReturn(mockOzellik);

        ResponseEntity<OzellikResponse> responseEntity = ozellikController.getOzellikById(ozellikId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(ozellikService, times(1)).getOzellikById(ozellikId);
    }
    @Test
    void getAllOzellikler() {
        List<Ozellik> mockOzellikler = Arrays.asList(new Ozellik(), new Ozellik());
        when(ozellikService.getAllOzellik()).thenReturn(mockOzellikler);

        ResponseEntity<List<OzellikResponse>> responseEntity = ozellikController.getAllOzellikler();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockOzellikler.size(), responseEntity.getBody().size());

        verify(ozellikService, times(1)).getAllOzellik();
    }
    @Test
    void addOzellik() {
        OzellikRequest mockOzellikRequest = new OzellikRequest();
        Ozellik mockAddedOzellik = new Ozellik();
        when(ozellikService.addOzellik(any(Ozellik.class))).thenReturn(mockAddedOzellik);

        ResponseEntity<OzellikResponse> responseEntity = ozellikController.addOzellik(mockOzellikRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(ozellikService, times(1)).addOzellik(any(Ozellik.class));
    }
    @Test
    void updateOzellik() {
        Long ozellikId = 1L;
        OzellikRequest mockOzellikRequest = new OzellikRequest();
        Ozellik mockUpdatedOzellik = new Ozellik();
        when(ozellikService.updateOzellik(any(Ozellik.class))).thenReturn(mockUpdatedOzellik);

        ResponseEntity<OzellikResponse> responseEntity = ozellikController.updateOzellik(ozellikId, mockOzellikRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(ozellikService, times(1)).updateOzellik(any(Ozellik.class));
    }
    @Test
    void deleteOzellik() {
        Long ozellikId = 1L;

        ResponseEntity<Void> responseEntity = ozellikController.deleteOzellik(ozellikId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        verify(ozellikService, times(1)).deleteOzellikById(ozellikId);
    }




}

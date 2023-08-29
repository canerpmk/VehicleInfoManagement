package com.example.sahibinden.controller;

import com.example.sahibinden.model.Ozellik;
import com.example.sahibinden.model.dto.OzellikRequest;
import com.example.sahibinden.model.dto.OzellikResponse;
import com.example.sahibinden.service.OzellikService;
import com.example.sahibinden.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

        Ozellik mockOzellik = TestUtils.ozellikBuilder();
        when(ozellikService.getOzellikById(mockOzellik.getId())).thenReturn(mockOzellik);

        ResponseEntity<OzellikResponse> responseEntity = ozellikController.getOzellikById(mockOzellik.getId());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(ozellikService, times(1)).getOzellikById(mockOzellik.getId());
    }

    @Test
    void getAllOzellikler() {
        List<Ozellik> mockOzellikler = List.of(TestUtils.ozellikBuilder(), TestUtils.ozellikBuilder());
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
        Ozellik mockAddedOzellik = TestUtils.ozellikBuilder();
        when(ozellikService.addOzellik(any(Ozellik.class))).thenReturn(mockAddedOzellik);

        ResponseEntity<OzellikResponse> responseEntity = ozellikController.addOzellik(mockOzellikRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(ozellikService, times(1)).addOzellik(any(Ozellik.class));
    }

    @Test
    void updateOzellik() {

        OzellikRequest mockOzellikRequest = new OzellikRequest();
        Ozellik mockUpdatedOzellik = TestUtils.ozellikBuilder();
        when(ozellikService.updateOzellik(any(Ozellik.class))).thenReturn(mockUpdatedOzellik);

        ResponseEntity<OzellikResponse> responseEntity = ozellikController.updateOzellik(mockUpdatedOzellik.getId(), mockOzellikRequest);

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

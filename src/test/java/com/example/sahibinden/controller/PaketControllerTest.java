package com.example.sahibinden.controller;

import com.example.sahibinden.model.Paket;
import com.example.sahibinden.model.dto.PaketResponse;
import com.example.sahibinden.service.impl.PaketServiceImpl;
import com.example.sahibinden.utils.TestUtils;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
public class PaketControllerTest {
    @Mock
    private PaketServiceImpl paketService;

    @InjectMocks
    private PaketController paketController;


    @Test
    public void testGetPaketById() {
        Long id = 1L;
        Paket expectedPaket = TestUtils.paketBuilder();
        PaketResponse expectedResponse = PaketResponse.fromModel(expectedPaket);

        when(paketService.getPaketById(id)).thenReturn(expectedPaket);


        ResponseEntity<PaketResponse> responseEntity = paketController.getPaketById(id);

        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}

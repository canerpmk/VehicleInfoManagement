package com.example.sahibinden.controller;

import com.example.sahibinden.model.*;
import com.example.sahibinden.model.dto.KasaResponse;
import com.example.sahibinden.model.dto.MarkaResponse;
import com.example.sahibinden.model.dto.ModelResponse;
import com.example.sahibinden.model.dto.MotorResponse;
import com.example.sahibinden.service.ParseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParseControllerTest {

    @Mock
    private ParseService parseService;

    @InjectMocks
    private ParseController parseController;


    @Test
    void testParseMarkaPage() {
        List<Marka> mockMarkaList = new ArrayList<>();
        mockMarkaList.add(new Marka());
        mockMarkaList.add(new Marka());

        when(parseService.parseMarkaPage()).thenReturn(mockMarkaList);

        ResponseEntity<List<Marka>> responseEntity = parseController.parseMarkaPage();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockMarkaList, responseEntity.getBody());

        verify(parseService, times(1)).parseMarkaPage();
    }

    @Test
    void testUpdateMarkaPage() {
        List<Marka> mockUpdatedMarkaList = new ArrayList<>();
        mockUpdatedMarkaList.add(new Marka());
        mockUpdatedMarkaList.add(new Marka());

        List<MarkaResponse> mockMarkaResponseList = mockUpdatedMarkaList.stream()
                .map(MarkaResponse::fromModel)
                .collect(Collectors.toList());

        when(parseService.updateMarkas()).thenReturn(mockUpdatedMarkaList);

        ResponseEntity<List<MarkaResponse>> responseEntity = parseController.updateMarkaPage();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockMarkaResponseList, responseEntity.getBody());

        verify(parseService, times(1)).updateMarkas();
    }

    @Test
    void testUpdateModelPage() {
        String mockMarkaPagePath = "mockMarkaPath";
        List<Model> mockUpdatedModelList = new ArrayList<>();
        mockUpdatedModelList.add(new Model());
        mockUpdatedModelList.add(new Model());

        List<ModelResponse> mockModelResponseList = mockUpdatedModelList.stream()
                .map(ModelResponse::fromModel)
                .collect(Collectors.toList());

        when(parseService.updateModels(mockMarkaPagePath)).thenReturn(mockUpdatedModelList);

        ResponseEntity<List<ModelResponse>> responseEntity = parseController.updateModelPage(mockMarkaPagePath);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockModelResponseList, responseEntity.getBody());

        verify(parseService, times(1)).updateModels(mockMarkaPagePath);
    }

    @Test
    void testParseModelPage() {
        String mockMarkaPagePath = "mockMarkaPath";
        List<Model> mockParsedModelList = new ArrayList<>();
        mockParsedModelList.add(new Model());
        mockParsedModelList.add(new Model());

        List<ModelResponse> mockModelResponseList = mockParsedModelList.stream()
                .map(ModelResponse::fromModel)
                .collect(Collectors.toList());

        when(parseService.parseModelPage(mockMarkaPagePath)).thenReturn(mockParsedModelList);

        ResponseEntity<List<ModelResponse>> responseEntity = parseController.parseModelPage(mockMarkaPagePath);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockModelResponseList, responseEntity.getBody());

        verify(parseService, times(1)).parseModelPage(mockMarkaPagePath);
    }

    @Test
    void testParseKasaPage() {
        String mockMarkaPagePath = "mockMarkaPath";
        String mockModelPagePath = "mockModelPath";
        List<Kasa> mockParsedKasaList = new ArrayList<>();
        mockParsedKasaList.add(new Kasa());
        mockParsedKasaList.add(new Kasa());

        List<KasaResponse> mockKasaResponseList = mockParsedKasaList.stream()
                .map(KasaResponse::fromModel)
                .collect(Collectors.toList());

        when(parseService.parseKasaPage(mockMarkaPagePath, mockModelPagePath)).thenReturn(mockParsedKasaList);

        ResponseEntity<List<KasaResponse>> responseEntity = parseController.parseKasaPage(mockMarkaPagePath, mockModelPagePath);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockKasaResponseList, responseEntity.getBody());

        verify(parseService, times(1)).parseKasaPage(mockMarkaPagePath, mockModelPagePath);
    }

    @Test
    void testUpdateKasaPage() {
        String mockMarkaPagePath = "mockMarkaPath";
        String mockModelPagePath = "mockModelPath";
        List<Kasa> mockUpdatedKasaList = new ArrayList<>();
        mockUpdatedKasaList.add(new Kasa());
        mockUpdatedKasaList.add(new Kasa());

        List<KasaResponse> mockKasaResponseList = mockUpdatedKasaList.stream()
                .map(KasaResponse::fromModel)
                .collect(Collectors.toList());

        when(parseService.updateKasas(mockMarkaPagePath, mockModelPagePath)).thenReturn(mockUpdatedKasaList);

        ResponseEntity<List<KasaResponse>> responseEntity = parseController.updateKasaPage(mockMarkaPagePath, mockModelPagePath);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockKasaResponseList, responseEntity.getBody());

        verify(parseService, times(1)).updateKasas(mockMarkaPagePath, mockModelPagePath);
    }

    @Test
    void testUpdateMotorPage() {
        String mockMarkaPagePath = "mockMarkaPath";
        String mockModelPagePath = "mockModelPath";
        String mockKasaPagePath = "mockKasaPath";
        List<Motor> mockUpdatedMotorList = new ArrayList<>();
        mockUpdatedMotorList.add(new Motor());
        mockUpdatedMotorList.add(new Motor());

        List<MotorResponse> mockMotorResponseList = mockUpdatedMotorList.stream()
                .map(MotorResponse::fromModel)
                .collect(Collectors.toList());

        when(parseService.updateMotors(mockMarkaPagePath, mockModelPagePath, mockKasaPagePath)).thenReturn(mockUpdatedMotorList);

        ResponseEntity<List<MotorResponse>> responseEntity = parseController.updateMotorPage(mockMarkaPagePath, mockModelPagePath, mockKasaPagePath);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockMotorResponseList, responseEntity.getBody());

        verify(parseService, times(1)).updateMotors(mockMarkaPagePath, mockModelPagePath, mockKasaPagePath);
    }

    @Test
    void testParseMotorPage() {
        List<Motor> mockParsedMotorList = new ArrayList<>();
        mockParsedMotorList.add(new Motor());
        mockParsedMotorList.add(new Motor());

        when(parseService.parseMotorrPage()).thenReturn(mockParsedMotorList);

        ResponseEntity<List<Motor>> responseEntity = parseController.parseMotorPage();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockParsedMotorList, responseEntity.getBody());

        verify(parseService, times(1)).parseMotorrPage();
    }

    @Test
    void testParseKasaaPage() {
        List<Kasa> mockKasaList = new ArrayList<>();
        Kasa mockKasa1 = new Kasa();
        mockKasa1.setId(1L);
        Kasa mockKasa2 = new Kasa();
        mockKasa2.setId(2L);
        mockKasaList.add(mockKasa1);
        mockKasaList.add(mockKasa2);

        when(parseService.parseKasaaPage()).thenReturn(mockKasaList);

        ResponseEntity<List<Kasa>> responseEntity = parseController.parseKasaPage();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(2, responseEntity.getBody().size());
        assertEquals(1L, responseEntity.getBody().get(0).getId());
        assertEquals(2L, responseEntity.getBody().get(1).getId());

        verify(parseService, times(1)).parseKasaaPage();
    }

    @Test
    void testParseOzellikPage() {
        List<Ozellik> mockOzellikList = new ArrayList<>();
        Ozellik mockOzellik1 = new Ozellik();
        mockOzellik1.setId(1L);
        Ozellik mockOzellik2 = new Ozellik();
        mockOzellik2.setId(2L);
        mockOzellikList.add(mockOzellik1);
        mockOzellikList.add(mockOzellik2);

        when(parseService.parseOzellikPage()).thenReturn(mockOzellikList);

        ResponseEntity<List<Ozellik>> responseEntity = parseController.parseOzellikPage();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(2, responseEntity.getBody().size());
        assertEquals(1L, responseEntity.getBody().get(0).getId());
        assertEquals(2L, responseEntity.getBody().get(1).getId());

        verify(parseService, times(1)).parseOzellikPage();
    }

}

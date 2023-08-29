package com.example.sahibinden.controller;

import com.example.sahibinden.model.*;
import com.example.sahibinden.model.dto.KasaResponse;
import com.example.sahibinden.model.dto.MarkaResponse;
import com.example.sahibinden.model.dto.ModelResponse;
import com.example.sahibinden.model.dto.MotorResponse;
import com.example.sahibinden.service.ParseService;
import com.example.sahibinden.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        List<Marka> mockMarkaList = List.of(TestUtils.markaBuilder(), TestUtils.markaBuilder());


        when(parseService.parseMarkaPage()).thenReturn(mockMarkaList);

        ResponseEntity<List<Marka>> responseEntity = parseController.parseMarkaPage();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockMarkaList, responseEntity.getBody());

        verify(parseService, times(1)).parseMarkaPage();
    }

    @Test
    void testUpdateMarkaPage() {
        List<Marka> mockUpdatedMarkaList = List.of(TestUtils.markaBuilder(), TestUtils.markaBuilder());


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
        String mockMarkaPagePath = TestUtils.mockMarkaPath();
        List<Model> mockUpdatedModelList = List.of(TestUtils.modelBuilder(), TestUtils.modelBuilder());


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
        String mockMarkaPagePath = TestUtils.mockMarkaPath();
        List<Model> mockParsedModelList = List.of(TestUtils.modelBuilder(), TestUtils.modelBuilder());


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
        String mockMarkaPagePath = TestUtils.mockMarkaPath();
        String mockModelPagePath = TestUtils.mockModelPath();
        List<Kasa> mockParsedKasaList = List.of(TestUtils.kasaBuilder(), TestUtils.kasaBuilder());


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
        String mockMarkaPagePath = TestUtils.mockMarkaPath();
        String mockModelPagePath = TestUtils.mockModelPath();
        List<Kasa> mockUpdatedKasaList = List.of(TestUtils.kasaBuilder(), TestUtils.kasaBuilder());


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
        String mockMarkaPagePath = TestUtils.mockMarkaPath();
        String mockModelPagePath = TestUtils.mockModelPath();
        String mockKasaPagePath = TestUtils.mockKasaPath();
        List<Motor> mockUpdatedMotorList = List.of(TestUtils.motorBuilder(), TestUtils.motorBuilder());

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
        List<Motor> mockParsedMotorList = List.of(TestUtils.motorBuilder(), TestUtils.motorBuilder());

        when(parseService.parseMotorrPage()).thenReturn(mockParsedMotorList);

        ResponseEntity<List<Motor>> responseEntity = parseController.parseMotorPage();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockParsedMotorList, responseEntity.getBody());

        verify(parseService, times(1)).parseMotorrPage();
    }

    @Test
    void testParseKasaaPage() {
        List<Kasa> mockKasaList = List.of(TestUtils.kasaBuilder(), TestUtils.kasaBuilder());


        when(parseService.parseKasaaPage()).thenReturn(mockKasaList);


        ResponseEntity<List<Kasa>> responseEntity = parseController.parseKasaPage();


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockKasaList, responseEntity.getBody());

        verify(parseService, times(1)).parseKasaaPage();
    }

    @Test
    void testParseOzellikPage() {
        List<Ozellik> mockOzellikList = List.of(TestUtils.ozellikBuilder(), TestUtils.ozellikBuilder());


        when(parseService.parseOzellikPage()).thenReturn(mockOzellikList);

        ResponseEntity<List<Ozellik>> responseEntity = parseController.parseOzellikPage();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockOzellikList, responseEntity.getBody());
        ;

        verify(parseService, times(1)).parseOzellikPage();
    }

}

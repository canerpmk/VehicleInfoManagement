package com.example.sahibinden.controller;

import com.example.sahibinden.model.Model;
import com.example.sahibinden.model.dto.ModelRequest;
import com.example.sahibinden.model.dto.ModelResponse;
import com.example.sahibinden.service.ModelService;
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
public class ModelControllerTest {
    @Mock
    private ModelService modelService;

    @InjectMocks
    private ModelController modelController;

    @Test
    void getModelById() {
        Model mockModel = TestUtils.modelBuilder();
        when(modelService.getModelById(mockModel.getId())).thenReturn(mockModel);

        ResponseEntity<ModelResponse> responseEntity = modelController.getModelById(mockModel.getId());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(modelService, times(1)).getModelById(mockModel.getId());
    }

    @Test
    void getAllModel() {
        List<Model> mockModeller = List.of(TestUtils.modelBuilder(), TestUtils.modelBuilder());
        when(modelService.getAllModel()).thenReturn(mockModeller);


        ResponseEntity<List<ModelResponse>> responseEntity = modelController.getAllModel();


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockModeller.size(), responseEntity.getBody().size());

        verify(modelService, times(1)).getAllModel();
    }

    @Test
    void addModel() {
        ModelRequest mockModelRequest = new ModelRequest();
        Model mockAddedModel = TestUtils.modelBuilder();
        when(modelService.addModel(any(Model.class))).thenReturn(mockAddedModel);

        ResponseEntity<ModelResponse> responseEntity = modelController.addModel(mockModelRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(modelService, times(1)).addModel(any(Model.class));
    }

    @Test
    void updateModel() {
        ModelRequest mockModelRequest = new ModelRequest();
        Model mockUpdatedModel = TestUtils.modelBuilder();
        when(modelService.updateModel(any(Model.class))).thenReturn(mockUpdatedModel);

        ResponseEntity<ModelResponse> responseEntity = modelController.updateModel(mockUpdatedModel.getId(), mockModelRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(modelService, times(1)).updateModel(any(Model.class));
    }

    @Test
    void deleteModel() {
        Long modelId = 1L;

        ResponseEntity<Void> responseEntity = modelController.deleteModel(modelId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        verify(modelService, times(1)).deleteModelById(modelId);
    }


}

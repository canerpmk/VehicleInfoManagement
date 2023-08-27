package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Model;
import com.example.sahibinden.model.entity.KasaEntity;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.model.entity.ModelEntity;
import com.example.sahibinden.repository.MarkaRepository;
import com.example.sahibinden.repository.ModelRepository;
import com.example.sahibinden.service.ModelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModelServiceTest {

    @Mock
    private ModelRepository modelRepository;

    @InjectMocks
    private ModelServiceImpl modelService;
    @Mock
    private MarkaRepository markaRepository;

    @Test
    void getMarkaById(){
        Long modelId = 1L;
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setId(modelId);
        modelEntity.setShortName("sedan");

        when(modelRepository.findById(modelId)).thenReturn(Optional.of(modelEntity));

        Model model = modelService.getModelById(modelId);

        assertEquals(modelId, model.getId());
        assertEquals("sedan", model.getShortName());
    }

    @Test
    void getShortNameById(){
        String shortName = "sedan";
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setId(1L);
        modelEntity.setShortName(shortName);

        when(modelRepository.findModelEntityByShortName(shortName)).thenReturn(Optional.of(modelEntity));

        Model model = modelService.getModelByShortName(shortName);

        assertEquals(1L, model.getId());
        assertEquals(shortName, model.getShortName());
    }


    @Test
    void getAllModel(){

        List<ModelEntity> modelEntityList = new ArrayList<>();
        ModelEntity modelEntity1 = new ModelEntity();
        modelEntity1.setId(1L);

        ModelEntity modelEntity2 = new ModelEntity();
        modelEntity2.setId(2L);

        modelEntityList.add(modelEntity1);
        modelEntityList.add(modelEntity2);

        when(modelRepository.findAll()).thenReturn(modelEntityList);


        List<Model> modelList = modelService.getAllModel();


        assertEquals(2, modelList.size());

        Model model1 = modelList.get(0);
        assertEquals(1L, model1.getId());

        Model model2 = modelList.get(1);
        assertEquals(2L, model2.getId());
    }

    @Test
    void addModel(){
        MarkaEntity mockMarkaEntity = new MarkaEntity();
        mockMarkaEntity.setId(1L);

        // Mock ModelEntity
        Model mockModel = new Model();
        mockModel.setId(1L);
        mockModel.setName("MockModelName");
        mockModel.setMarka(new Marka());

        when(markaRepository.findById(1L)).thenReturn(Optional.of(mockMarkaEntity));
        when(modelRepository.save(any(ModelEntity.class))).thenAnswer(invocation -> {
            ModelEntity savedEntity = invocation.getArgument(0);
            savedEntity.setId(1L); // Set a mock ID
            return savedEntity;
        });

        // Call the method
        Model result = modelService.addModel(mockModel);

        // Verify
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("MockModelName", result.getName());
        assertNotNull(result.getMarka());
        assertEquals(1L, result.getMarka().getId());

        verify(markaRepository, times(1)).findById(1L);
        verify(modelRepository, times(1)).save(any(ModelEntity.class));

    }


    @Test
    void updateModel(){
        // Mocked model and modelEntity
        Model model = new Model();
        model.setId(1L);
        ModelEntity modelEntity = ModelEntity.fromModel(model);

        // Stubbing
        when(modelRepository.existsById(1L)).thenReturn(true);
        when(modelRepository.save(any(ModelEntity.class))).thenReturn(modelEntity);

        // Call the method
        Model updatedModel = modelService.updateModel(model);

        // Verification
        assertNotNull(updatedModel);
        assertEquals(model.getId(), updatedModel.getId());

        // Verify interactions
        verify(modelRepository).existsById(1L);
        verify(modelRepository).save(any(ModelEntity.class));
        verifyNoMoreInteractions(modelRepository);

    }
    @Test
    void addModels() {
        // Mocked modelList and modelEntityList
        List<Model> modelList = new ArrayList<>();
        Model model1 = new Model();
        model1.setId(1L);
        modelList.add(model1);

        List<ModelEntity> modelEntityList = modelList.stream()
                .map(ModelEntity::fromModel)
                .toList();

        // Stubbing
        when(modelRepository.saveAll(anyList())).thenReturn(modelEntityList);

        // Call the method
        List<Model> addedModels = modelService.addModels(modelList);

        // Verification
        assertNotNull(addedModels);
        assertEquals(modelList.size(), addedModels.size());
        assertEquals(modelList.get(0).getId(), addedModels.get(0).getId());

        // Verify interactions
        verify(modelRepository).saveAll(anyList());
        verifyNoMoreInteractions(modelRepository);
    }
    @Test
    void deleteModel(){
        Long modelId = 1L;

        when(modelRepository.existsById(modelId)).thenReturn(false);
        doNothing().when(modelRepository).deleteById(modelId);

        boolean isDeleted = modelService.deleteModelById(modelId);


        assertTrue(isDeleted);

        verify(modelRepository).existsById(modelId);
        verify(modelRepository).deleteById(modelId);

    }
}

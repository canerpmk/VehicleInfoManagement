package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Model;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.model.entity.ModelEntity;
import com.example.sahibinden.repository.MarkaRepository;
import com.example.sahibinden.repository.ModelRepository;
import com.example.sahibinden.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
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
    void getModelById() {

        ModelEntity modelEntity = TestUtils.modelEntity(TestUtils.modelEntity().getId(), TestUtils.modelEntity().getShortName());


        when(modelRepository.findById(modelEntity.getId())).thenReturn(Optional.of(modelEntity));

        Model model = modelService.getModelById(modelEntity.getId());

        assertEquals(modelEntity.getId(), model.getId());
        assertEquals(modelEntity.getShortName(), model.getShortName());
    }

    @Test
    void getShortNameById() {

        ModelEntity modelEntity = TestUtils.modelEntity(TestUtils.modelEntity().getId(), TestUtils.modelEntity().getShortName());

        when(modelRepository.findModelEntityByShortName(modelEntity.getShortName())).thenReturn(Optional.of(modelEntity));

        Model model = modelService.getModelByShortName(modelEntity.getShortName());

        assertEquals(modelEntity.getId(), model.getId());
        assertEquals(modelEntity.getShortName(), model.getShortName());
    }


    @Test
    void getAllModel() {

        List<ModelEntity> modelEntityList = List.of(TestUtils.modelEntity(), TestUtils.modelEntity());


        when(modelRepository.findAll()).thenReturn(modelEntityList);


        List<Model> modelList = modelService.getAllModel();


        assertEquals(modelEntityList.size(), modelList.size());

        Model model1 = modelList.get(0);
        assertEquals(modelEntityList.get(0).getId(), model1.getId());

        Model model2 = modelList.get(1);
        assertEquals(modelEntityList.get(1).getId(), model2.getId());
    }

    @Test
    void testAddModel() {
        Long markaId = 1L;
        Long modelId = 2L;

        MarkaEntity mockMarkaEntity = TestUtils.markaEntity(markaId);

        Model mockModel = TestUtils.modelBuilder(modelId);
        mockModel.setName("MockModelName");
        mockModel.setMarka(TestUtils.markaBuilder(markaId));


        when(markaRepository.findById(markaId)).thenReturn(Optional.of(mockMarkaEntity));


        when(modelRepository.save(any(ModelEntity.class))).thenAnswer(invocation -> {
            ModelEntity savedEntity = invocation.getArgument(0);
            savedEntity.setId(modelId); // Set a mock ID
            return savedEntity;
        });


        Model result = modelService.addModel(mockModel);


        assertEquals(modelId, result.getId());
        assertEquals("MockModelName", result.getName());
        assertEquals(markaId, result.getMarka().getId());

        verify(markaRepository, times(1)).findById(markaId);
        verify(modelRepository, times(1)).save(any(ModelEntity.class));
    }


    @Test
    void updateModel() {

        Model model = TestUtils.modelBuilder();

        ModelEntity modelEntity = ModelEntity.fromModel(model);


        when(modelRepository.existsById(model.getId())).thenReturn(true);
        when(modelRepository.save(any(ModelEntity.class))).thenReturn(modelEntity);


        Model updatedModel = modelService.updateModel(model);


        assertNotNull(updatedModel);
        assertEquals(model.getId(), updatedModel.getId());


        verify(modelRepository).existsById(model.getId());
        verify(modelRepository).save(any(ModelEntity.class));
        verifyNoMoreInteractions(modelRepository);

    }

    @Test
    void addModels() {

        List<Model> modelList = Arrays.asList(TestUtils.modelBuilder(1L));
        List<ModelEntity> modelEntityList = modelList.stream()
                .map(ModelEntity::fromModel)
                .toList();


        when(modelRepository.saveAll(anyList())).thenReturn(modelEntityList);


        List<Model> addedModels = modelService.addModels(modelList);


        assertNotNull(addedModels);
        assertEquals(modelList.size(), addedModels.size());
        assertEquals(modelList.get(0).getId(), addedModels.get(0).getId());


        verify(modelRepository).saveAll(anyList());
        verifyNoMoreInteractions(modelRepository);
    }

    @Test
    void deleteModel() {
        Long modelId = TestUtils.randomId();

        when(modelRepository.existsById(modelId)).thenReturn(false);
        doNothing().when(modelRepository).deleteById(modelId);

        boolean isDeleted = modelService.deleteModelById(modelId);


        assertTrue(isDeleted);

        verify(modelRepository).existsById(modelId);
        verify(modelRepository).deleteById(modelId);

    }
}

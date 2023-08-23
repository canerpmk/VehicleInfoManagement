package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Motor;
import com.example.sahibinden.model.entity.MotorEntity;
import com.example.sahibinden.repository.MotorRepository;
import com.example.sahibinden.service.MotorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MotorServiceImpl implements MotorService {
    private final MotorRepository motorRepository;

    public Motor getMotorById(Long id) {
        MotorEntity motorEntity = motorRepository.findById(id).orElseThrow();
        return Motor.fromEntity(motorEntity);
    }

    public List<Motor> getAllMotor() {
        List<MotorEntity> motorEntities = motorRepository.findAll();
        return motorEntities.stream()
                .map(Motor::fromEntity)
                .collect(Collectors.toList());
    }

    public Motor getKMotorByShortName(String shortName) {
        MotorEntity motorEntity = motorRepository.findMotorEntityByShortName(shortName).orElseThrow();
        return Motor.fromEntity(motorEntity);
    }

    public Motor addMotor(Motor motor) {
        MotorEntity motorEntity = MotorEntity.fromModel(motor);
        MotorEntity addedMotorEntity = motorRepository.save(motorEntity);
        return Motor.fromEntity(addedMotorEntity);
    }

    public List<Motor> addMotors(List<Motor> motorList) {
        List<MotorEntity> motorEntityList = motorList.stream().map(MotorEntity::fromModel).toList();
        List<MotorEntity> addedMotorEntityList = motorRepository.saveAll(motorEntityList);
        return addedMotorEntityList.stream().map(Motor::fromEntity).toList();
    }

    public Motor updateMotor(Motor motor) {
        if (motorRepository.existsById(motor.getId())) {
            MotorEntity updatedMarkaEntity = motorRepository.save(MotorEntity.fromModel(motor));
            return Motor.fromEntity(updatedMarkaEntity);
        }
        return null;
    }

    public boolean deleteMotorById(Long id) {
        motorRepository.deleteById(id);
        return !motorRepository.existsById(id);
    }
}

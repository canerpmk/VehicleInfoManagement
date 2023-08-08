package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Motor;
import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.model.entity.MotorEntity;
import com.example.sahibinden.repository.MarkaRepository;
import com.example.sahibinden.repository.MotorRepository;
import com.example.sahibinden.exception.model.CustomException;
import com.example.sahibinden.service.MotorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MotorServiceImpl implements MotorService {
    private final MotorRepository motorRepository;

    public Motor getMotorById(Long id) {
        MotorEntity motorEntity = motorRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id));
        return Motor.fromEntity(motorEntity);
    }

    public List<Motor> getAllMotor() {
        List<MotorEntity> motorEntities = motorRepository.findAll();
        return motorEntities.stream()
                .map(Motor::fromEntity)
                .collect(Collectors.toList());
    }

    public Motor addMotor(Motor motor) {
        MotorEntity motorEntity = MotorEntity.fromModel(motor);
        MotorEntity addedMotorEntity = motorRepository.save(motorEntity);
        return Motor.fromEntity(addedMotorEntity);
    }

    public Motor updateMotor(Motor motor) {
        if (motorRepository.existsById(motor.getId())) {
            MotorEntity updatedMotorEntity = motorRepository.save(MotorEntity.fromModel(motor));
            return Motor.fromEntity(updatedMotorEntity);
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + motor.getId());
    }

    public boolean deleteMotorById(Long id) {
        if (motorRepository.existsById(id)) {
            motorRepository.deleteById(id);
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id);
    }
}

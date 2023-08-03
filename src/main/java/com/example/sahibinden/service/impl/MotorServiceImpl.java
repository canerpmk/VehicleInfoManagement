package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.entity.MotorEntity;
import com.example.sahibinden.repository.MotorRepository;
import com.example.sahibinden.service.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorServiceImpl {
    private final MotorRepository motorRepository;

    public MotorEntity getMotorById(Long id) {
        return motorRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id));
    }

    public List<MotorEntity> getAllMotor() {
        return motorRepository.findAll();
    }

    public MotorEntity addMotor(MotorEntity motor) {
        return motorRepository.save(motor);
    }

    public MotorEntity updateMotor(MotorEntity updatedMotor) {
        if (motorRepository.existsById(updatedMotor.getId())) {
            return motorRepository.save(updatedMotor);
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + updatedMotor.getId());
    }

    public boolean deleteMotorById(Long id) {
        if (motorRepository.existsById(id)) {
            motorRepository.deleteById(id);
            return true;
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id);
    }
}

package com.example.sahibinden.aspect;

import com.example.sahibinden.exception.model.NotFoundException;
import com.example.sahibinden.repository.*;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckExist {
    private final CarRepository carRepository;
    private final MarkaRepository markaRepository;
    private final ModelRepository modelRepository;
    private final MotorRepository motorRepository;
    private final OzellikRepository ozellikRepository;
    private final PaketRepository paketRepository;
    private final KasaRepository kasaRepository;


    @Before("execution(* com.example.sahibinden.service.*Service.*(Long, ..)) && args(id, ..)")
    public void checkEntityExistence(Long id) {
        JpaRepository[] repositories = {carRepository, markaRepository, modelRepository, motorRepository, ozellikRepository, paketRepository, kasaRepository};

        boolean idExists = Arrays.stream(repositories)
                .anyMatch(repository -> repository.existsById(id));

        if (!idExists) {
            throw new NotFoundException("Girdiğiniz id bulunamadı: " + id);
        }
        if (!idExists) {
            throw new NotFoundException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: ");
        }
    }
}






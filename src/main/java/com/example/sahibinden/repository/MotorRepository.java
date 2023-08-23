package com.example.sahibinden.repository;

import com.example.sahibinden.model.entity.MotorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotorRepository extends JpaRepository<MotorEntity, Long> {
    Optional<MotorEntity> findMotorEntityByShortName(String shortName);
}

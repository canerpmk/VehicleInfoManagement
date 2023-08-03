package com.example.sahibinden.repository;

import com.example.sahibinden.model.entity.CarEntity;
import com.example.sahibinden.model.entity.MotorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorRepository extends JpaRepository<MotorEntity,Long> {
}

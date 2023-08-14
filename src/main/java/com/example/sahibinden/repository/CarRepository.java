
package com.example.sahibinden.repository;

import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {


}
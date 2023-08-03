package com.example.sahibinden.repository;

import com.example.sahibinden.model.entity.CarEntity;
import com.example.sahibinden.model.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity,Long> {
}

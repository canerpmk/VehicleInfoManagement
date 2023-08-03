package com.example.sahibinden.repository;

import com.example.sahibinden.model.entity.CarEntity;
import com.example.sahibinden.model.entity.OzellikEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OzellikRepository extends JpaRepository<OzellikEntity,Long> {
}

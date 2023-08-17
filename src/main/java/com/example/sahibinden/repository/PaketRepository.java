package com.example.sahibinden.repository;

import com.example.sahibinden.model.entity.PaketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaketRepository extends JpaRepository<PaketEntity, Long> {
}

package com.example.sahibinden.repository;

import com.example.sahibinden.model.entity.KasaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KasaRepository extends JpaRepository<KasaEntity, Long> {
    Optional<KasaEntity> findKasaEntityByShortName(String shortName);
}

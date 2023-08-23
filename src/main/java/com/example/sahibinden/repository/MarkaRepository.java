package com.example.sahibinden.repository;

import com.example.sahibinden.model.entity.MarkaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarkaRepository extends JpaRepository<MarkaEntity, Long> {
    Optional<MarkaEntity> findMarkaEntityByShortName(String shortName);


}

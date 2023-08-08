package com.example.sahibinden.model;

import com.example.sahibinden.model.entity.MarkaEntity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Marka {
    @Id
    private Long id;
    private String name;

    public static Marka fromEntity(MarkaEntity markaEntity) {
        return Marka.builder()
                .id(markaEntity.getId())
                .name(markaEntity.getName())
                .build();
    }
}
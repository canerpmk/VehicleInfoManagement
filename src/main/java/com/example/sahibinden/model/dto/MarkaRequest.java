package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Marka;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkaRequest {
    private String name;

    public Marka toModel() {
        return Marka.builder()
                .name(name)
                .build();
    }
}
package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Paket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaketRequest {
    private String paket;

    public Paket toModel() {
        return Paket.builder()
                .paket(paket)
                .build();
    }
}

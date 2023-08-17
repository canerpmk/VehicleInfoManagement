package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KasaResponse {
    private Long id;
    private String tip;
    private Marka marka;
    private Model model;


    public static KasaResponse fromModel(Kasa kasa) {
        return KasaResponse.builder()
                .id(kasa.getId())
                .tip(kasa.getTip())
                .marka(kasa.getMarka())
                .model(kasa.getModel())
                .build();
    }
}

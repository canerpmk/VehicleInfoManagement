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
public class KasaRequest {
    private String tip;
    private Long model_id;
    private Long marka_id;
    public Kasa toModel() {
        return Kasa.builder()
                .tip(tip)
                .marka(Marka.builder().id(marka_id).build())
                .model(Model.builder().id(model_id).build())
                .build();
    }
}

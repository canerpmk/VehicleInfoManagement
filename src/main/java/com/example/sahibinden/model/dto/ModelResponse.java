package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelResponse {
    private Long id;
    private String name;
    private String shortName;
    private MarkaResponse marka;
    private List<KasaResponse> kasaList;

    public static ModelResponse fromModel(Model model) {
        return model==null ? null :ModelResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .shortName(model.getShortName())
                .kasaList(model.getKasaList().stream().map(KasaResponse::fromModel).toList())
                .marka(MarkaResponse.fromModel(model.getMarka()))
                .build();
    }


}

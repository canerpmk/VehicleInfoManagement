package com.example.sahibinden.model.dto;

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
public class ModelResponse {
    private Long id;
    private String name;
    private String shortName;
    private Marka marka;

    public static ModelResponse fromModel(Model model) {
        return ModelResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .shortName(model.getShortName())
                .marka(model.getMarka())
                .build();
    }


}

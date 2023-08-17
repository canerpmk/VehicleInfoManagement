package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Marka;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkaResponse {
    private Long id;
    private String name;
    private String shortName;
    private List<ModelResponse> modelList;

    public static MarkaResponse fromModel(Marka marka) {
        return MarkaResponse.builder()
                .id(marka.getId())
                .name(marka.getName())
                .shortName(marka.getShortName())
                .modelList(marka.getModelList().stream().map(ModelResponse::fromModel).toList())
                .build();
    }

}
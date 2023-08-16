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
public class MarkaResponse {
    private Long id;
    private String name;

    public static MarkaResponse fromModel(Marka marka) {
        return MarkaResponse.builder()
                .id(marka.getId())
                .name(marka.getName())
                .build();
    }

}
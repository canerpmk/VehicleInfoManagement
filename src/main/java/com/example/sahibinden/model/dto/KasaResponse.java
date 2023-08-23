package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Kasa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KasaResponse {
    private Long id;
    private String kasatip;
    private String shortName;
    private String imgUrl;
    private String motortip;
    private List<MotorResponse> motorList;

    public static KasaResponse fromModel(Kasa kasa) {
        return KasaResponse.builder()
                .id(kasa.getId())
                .kasatip(kasa.getKasatip())
                .imgUrl(kasa.getImgUrl())
                .motortip(kasa.getMotortip())
                .shortName(kasa.getShortName())
                .motorList(kasa.getMotorList().stream().map(MotorResponse::fromModel).toList())
                .build();
    }
}

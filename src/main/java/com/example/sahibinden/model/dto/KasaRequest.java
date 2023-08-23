package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Kasa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KasaRequest {
    private String kasatip;
    private String shortName;
    private String imgUrl;
    private String motortip;

    public Kasa toModel() {
        return Kasa.builder()
                .kasatip(kasatip)
                .motortip(motortip)
                .shortName(shortName)
                .imgUrl(imgUrl)
                .build();
    }
}

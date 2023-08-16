package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Motor;
import com.example.sahibinden.model.Ozellik;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OzellikResponse {
    private Long id;
    private Double vites_sayisi;
    private Double yakit_deposu;
    private Double max_hiz;
    private Double si_tuketim;
    private Double sd_tuketim;
    private Double ort_tuketim;
    private int bagaj_hacmi;
    private String yakit_tur;
    public static OzellikResponse fromModel(Ozellik ozellik) {
        return OzellikResponse.builder()
                .id(ozellik.getId())
                .vites_sayisi(ozellik.getVites_sayisi())
                .yakit_deposu(ozellik.getYakit_deposu())
                .max_hiz(ozellik.getMax_hiz())
                .si_tuketim(ozellik.getSi_tuketim())
                .sd_tuketim(ozellik.getSd_tuketim())
                .ort_tuketim(ozellik.getOrt_tuketim())
                .bagaj_hacmi(ozellik.getBagaj_hacmi())
                .yakit_tur(ozellik.getYakit_tur())
                .build();
    }


}

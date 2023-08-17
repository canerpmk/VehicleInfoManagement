package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Ozellik;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OzellikRequest {
    private Double vites_sayisi;
    private Double yakit_deposu;
    private Double max_hiz;
    private Double si_tuketim;
    private Double sd_tuketim;
    private Double ort_tuketim;
    private int bagaj_hacmi;
    private String yakit_tur;

    public Ozellik toModel() {
        return Ozellik.builder()

                .vites_sayisi(vites_sayisi)
                .yakit_deposu(yakit_deposu)
                .max_hiz(max_hiz)
                .si_tuketim(si_tuketim)
                .sd_tuketim(sd_tuketim)
                .ort_tuketim(ort_tuketim)
                .bagaj_hacmi(bagaj_hacmi)
                .yakit_tur(yakit_tur)
                .build();
    }

}

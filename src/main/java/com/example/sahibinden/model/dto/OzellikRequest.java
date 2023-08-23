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
    private String vites_kutusu;
    private Double yakit_deposu;
    private Double max_hiz;
    private String si_tuketim;
    private String sd_tuketim;
    private String ort_tuketim;
    private int bagaj_hacmi;
    private String yakit_tur;

    private Double motorgucu;
    private Double motorhacmi;
    private Double silindirsayisi;
    private Double tork;

    public Ozellik toModel() {
        return Ozellik.builder()

                .vites_kutusu(vites_kutusu)
                .yakit_deposu(yakit_deposu)
                .max_hiz(max_hiz)
                .si_tuketim(si_tuketim)
                .sd_tuketim(sd_tuketim)
                .ort_tuketim(ort_tuketim)
                .bagaj_hacmi(bagaj_hacmi)
                .yakit_tur(yakit_tur)
                .motorgucu(motorgucu)
                .motorhacmi(motorhacmi)
                .silindirsayisi(silindirsayisi)
                .tork(tork)
                .build();
    }

}

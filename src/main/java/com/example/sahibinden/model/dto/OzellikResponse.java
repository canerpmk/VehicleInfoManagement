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
public class OzellikResponse {
    private Long id;
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

    public static OzellikResponse fromModel(Ozellik ozellik) {
        return OzellikResponse.builder()
                .id(ozellik.getId())
                .vites_kutusu(ozellik.getVites_kutusu())
                .yakit_deposu(ozellik.getYakit_deposu())
                .max_hiz(ozellik.getMax_hiz())
                .si_tuketim(ozellik.getSi_tuketim())
                .sd_tuketim(ozellik.getSd_tuketim())
                .ort_tuketim(ozellik.getOrt_tuketim())
                .bagaj_hacmi(ozellik.getBagaj_hacmi())
                .yakit_tur(ozellik.getYakit_tur())
                .motorgucu(ozellik.getMotorgucu())
                .motorhacmi(ozellik.getMotorhacmi())
                .silindirsayisi(ozellik.getSilindirsayisi())
                .tork(ozellik.getTork())
                .build();
    }


}

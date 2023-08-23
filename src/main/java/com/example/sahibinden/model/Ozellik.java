package com.example.sahibinden.model;

import com.example.sahibinden.model.entity.OzellikEntity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ozellik {
    @Id
    private Long id;
    private String vites_kutusu;
    private Double yakit_deposu;
    private Double max_hiz;
    private String si_tuketim;
    private String sd_tuketim;
    private String ort_tuketim;
    private Integer bagaj_hacmi;
    private String yakit_tur;
    private String shortname;


    private Double motorgucu;
    private Double motorhacmi;
    private Double silindirsayisi;
    private Double tork;

    public static Ozellik fromEntity(OzellikEntity ozellikEntity) {
        return Ozellik.builder()
                .id(ozellikEntity.getId())
                .vites_kutusu(ozellikEntity.getVites_kutusu())
                .yakit_deposu(ozellikEntity.getYakit_deposu())
                .max_hiz(ozellikEntity.getMax_hiz())
                .si_tuketim(ozellikEntity.getSi_tuketim())
                .sd_tuketim(ozellikEntity.getSd_tuketim())
                .ort_tuketim(ozellikEntity.getOrt_tuketim())
                .bagaj_hacmi(ozellikEntity.getBagaj_hacmi())
                .yakit_tur(ozellikEntity.getYakit_tur())
                .motorgucu(ozellikEntity.getMotorgucu())
                .motorhacmi(ozellikEntity.getMotorhacmi())
                .silindirsayisi(ozellikEntity.getSilindirsayisi())
                .tork(ozellikEntity.getTork())
                .build();
    }

}

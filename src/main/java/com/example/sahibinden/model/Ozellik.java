
package com.example.sahibinden.model;

import com.example.sahibinden.model.entity.MotorEntity;
import com.example.sahibinden.model.entity.OzellikEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    private Double vites_sayisi;
    private Double yakit_deposu;
    private Double max_hiz;
    private Double si_tuketim;
    private Double sd_tuketim;
    private Double ort_tuketim;
    private int bagaj_hacmi;
    private String yakit_tur;
    private String shortname;

    public static Ozellik fromEntity(OzellikEntity ozellikEntity) {
        return Ozellik.builder()
                .id(ozellikEntity.getId())
                .vites_sayisi(ozellikEntity.getVites_sayisi())
                .yakit_deposu(ozellikEntity.getYakit_deposu())
                .max_hiz(ozellikEntity.getMax_hiz())
                .si_tuketim(ozellikEntity.getSi_tuketim())
                .sd_tuketim(ozellikEntity.getSd_tuketim())
                .ort_tuketim(ozellikEntity.getOrt_tuketim())
                .bagaj_hacmi(ozellikEntity.getBagaj_hacmi())
                .yakit_tur(ozellikEntity.getYakit_tur())
                .build();
    }

}

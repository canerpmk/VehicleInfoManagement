package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Ozellik;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Ozellik")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OzellikEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vites_kutusu;
    private Double yakit_deposu;
    private Double max_hiz;
    private String si_tuketim;
    private String sd_tuketim;
    private String ort_tuketim;
    private Integer bagaj_hacmi;
    private String yakit_tur;


    private Double motorgucu;
    private Double motorhacmi;
    private Double silindirsayisi;
    private Double tork;

    public static OzellikEntity fromModel(Ozellik ozellik) {
        if (ozellik==null){
            return null;
        }
       return OzellikEntity.builder()
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
               .tork(ozellik.getTork()).build();

    }


}

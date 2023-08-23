package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Ozellik;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Ozellik")
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
        OzellikEntity ozellikEntity = new OzellikEntity();
        ozellikEntity.setId(ozellik.getId());
        ozellikEntity.setVites_kutusu(ozellik.getVites_kutusu());
        ozellikEntity.setYakit_deposu(ozellik.getYakit_deposu());
        ozellikEntity.setMax_hiz(ozellik.getMax_hiz());
        ozellikEntity.setSi_tuketim(ozellik.getSi_tuketim());
        ozellikEntity.setSd_tuketim(ozellik.getSd_tuketim());
        ozellikEntity.setOrt_tuketim(ozellik.getOrt_tuketim());
        ozellikEntity.setBagaj_hacmi(ozellik.getBagaj_hacmi());
        ozellikEntity.setYakit_tur(ozellik.getYakit_tur());
        ozellikEntity.setMotorgucu(ozellik.getMotorgucu());
        ozellikEntity.setMotorhacmi(ozellik.getMotorhacmi());
        ozellikEntity.setSilindirsayisi(ozellik.getSilindirsayisi());
        ozellikEntity.setTork(ozellik.getTork());
        return ozellikEntity;
    }


}

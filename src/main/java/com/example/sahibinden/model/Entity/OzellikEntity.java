package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Motor;
import com.example.sahibinden.model.Ozellik;
import com.example.sahibinden.model.Paket;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Ozellik")
public class OzellikEntity extends Ozellik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double vites_sayisi;
    private Double yakit_deposu;
    private Double max_hiz;
    private Double si_tuketim;
    private Double sd_tuketim;
    private Double ort_tuketim;
    private int bagaj_hacmi;
    private String yakit_tur;

    public static OzellikEntity fromModel(Ozellik ozellik){
        OzellikEntity ozellikEntity=new OzellikEntity();
        ozellikEntity.setId(ozellik.getId());
        ozellikEntity.setVites_sayisi(ozellik.getVites_sayisi());
        ozellikEntity.setYakit_deposu(ozellik.getYakit_deposu());
        ozellikEntity.setMax_hiz(ozellik.getMax_hiz());
        ozellikEntity.setSi_tuketim(ozellik.getSi_tuketim());
        ozellikEntity.setSd_tuketim(ozellik.getSd_tuketim());
        ozellikEntity.setOrt_tuketim(ozellik.getOrt_tuketim());
        ozellikEntity.setBagaj_hacmi(ozellik.getBagaj_hacmi());
        ozellikEntity.setYakit_tur(ozellik.getYakit_tur());
        return ozellikEntity;
    }

}

package com.example.sahibinden.model.Entity;

import com.example.sahibinden.model.Paket;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OzellikEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Double vites_sayisi;
    @Column
    private Double yakit_deposu;
    @Column
    private Double max_hiz;
    @Column
    private Double si_tuketim;
    @Column
    private Double sd_tuketim;
    @Column
    private Double ort_tuketim;
    @Column
    private int bagaj_hacmi;
    @Column
    private String yakit_tur;


}

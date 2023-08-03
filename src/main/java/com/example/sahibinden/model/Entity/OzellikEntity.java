package com.example.sahibinden.model.entity;

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
public class OzellikEntity {
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


}

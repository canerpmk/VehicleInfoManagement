
package com.example.sahibinden.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class CarOzellik {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ozellik_id;
    private int vites_sayisi;
    private int yakit_deposu;
    private int max_hiz;
    private double si_tuketim;
    private double sd_tuketim;
    private double ort_tuketim;
    private int bagaj_hacmi;
    private String yakit_tur;


}

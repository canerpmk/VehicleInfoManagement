package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Kasa;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Kasa")
public class KasaEntity extends Kasa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tip;

    @ManyToOne
    private ModelEntity model;

    @ManyToOne
    private MarkaEntity marka;

    public static KasaEntity fromModel(Kasa kasa) {
        KasaEntity kasaEntity = new KasaEntity();
        kasaEntity.setId(kasa.getId());
        kasaEntity.setTip(kasa.getTip());
        return kasaEntity;
    }

}

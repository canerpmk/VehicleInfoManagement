package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Kasa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.example.sahibinden.common.Utils.collectionAsStream;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Kasa")
public class KasaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kasatip;

    private String shortName;
    private String imgUrl;
    private String motortip;

    @ManyToOne
    private ModelEntity model;

    @OneToMany
    @JsonIgnore
    private List<MotorEntity> motor;


    public static KasaEntity fromModel(Kasa kasa) {
        KasaEntity kasaEntity = new KasaEntity();
        kasaEntity.setId(kasa.getId());
        kasaEntity.setKasatip(kasa.getKasatip());
        kasaEntity.setShortName(kasa.getShortName());
        kasaEntity.setImgUrl(kasa.getImgUrl());
        kasaEntity.setMotortip(kasa.getMotortip());
        kasaEntity.setModel(ModelEntity.fromModel(kasa.getModel()));
        kasaEntity.setMotor(collectionAsStream(kasa.getMotorList()).map(MotorEntity::fromModel).toList());
        return kasaEntity;
    }

}

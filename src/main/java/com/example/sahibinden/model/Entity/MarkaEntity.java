package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Marka;
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
@Table(name = "Marka")
public class MarkaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortName;
    private String imgUrl;
    private String info;

    @OneToMany
    @JsonIgnore
    private List<ModelEntity> model;

    public static MarkaEntity fromModel(Marka marka) {
        MarkaEntity markaEntity = new MarkaEntity();
        markaEntity.setId(marka.getId());
        markaEntity.setName(marka.getName());
        markaEntity.setShortName(marka.getShortName());
        markaEntity.setImgUrl(marka.getImgUrl());
        markaEntity.setInfo(marka.getInfo());
        markaEntity.setModel(collectionAsStream(marka.getModelList()).map(ModelEntity::fromModel).toList());
        return markaEntity;
    }


}

package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.Marka;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Marka")
public class MarkaEntity extends Marka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    @JsonIgnore
    private List<ModelEntity> model;
    public static MarkaEntity fromModel(Marka marka){
        MarkaEntity markaEntity = new MarkaEntity();
        markaEntity.setId(marka.getId());
        markaEntity.setName(marka.getName());
        return markaEntity;
    }

}

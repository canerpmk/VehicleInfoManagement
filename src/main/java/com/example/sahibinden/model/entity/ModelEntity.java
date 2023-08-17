package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Model;
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
@Table(name = "Model")
public class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortName;

    @ManyToOne
    private MarkaEntity marka;


    @OneToMany
    @JsonIgnore
    private List<KasaEntity> kasa;

    public static ModelEntity fromModel(Model model) {
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setId(model.getId());
        modelEntity.setName(model.getName());
        modelEntity.setShortName(model.getShortName());
        modelEntity.setMarka(MarkaEntity.fromModel(model.getMarka()));
        return modelEntity;
    }


}

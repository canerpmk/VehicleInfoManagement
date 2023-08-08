package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Model")
public class ModelEntity extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private MarkaEntity marka;

    public static ModelEntity fromModel(Model model) {
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setId(model.getId());
        modelEntity.setName(model.getName());
        return modelEntity;
    }


}

package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Marka;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static com.example.sahibinden.common.Utils.collectionAsStream;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Marka")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
        if (marka==null){
            return null;
        }
        return MarkaEntity.builder()
                .id(marka.getId())
                .name(marka.getName())
                .shortName(marka.getShortName())
                .imgUrl(marka.getImgUrl())
                .info(marka.getInfo())
                .model(collectionAsStream(marka.getModelList()).map(ModelEntity::fromModel).toList()).build();

    }


}

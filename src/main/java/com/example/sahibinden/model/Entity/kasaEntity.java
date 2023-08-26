package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Kasa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static com.example.sahibinden.common.Utils.collectionAsStream;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Kasa")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KasaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kasatip;
    private String yil;
    private String shortName;
    private String imgUrl;
    private String motortip;

    @ManyToOne
    private ModelEntity model;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MotorEntity> motor;


    public static KasaEntity fromModel(Kasa kasa) {
        if (kasa==null){
            return null;
        }
      return  KasaEntity.builder()
                .id(kasa.getId())
                .yil(kasa.getYil())
                .kasatip(kasa.getKasaTip())
                .shortName(kasa.getShortName())
                .imgUrl(kasa.getImgUrl())
                .motortip(kasa.getMotorTip())
                .model(ModelEntity.fromModel(kasa.getModel()))
                .motor(collectionAsStream(kasa.getMotorList()).map(MotorEntity::fromModel).toList())
              .build();


    }

}

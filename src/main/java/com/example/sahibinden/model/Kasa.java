package com.example.sahibinden.model;

import com.example.sahibinden.model.entity.KasaEntity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.example.sahibinden.common.Utils.collectionAsStream;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Kasa {
    @Id
    private Long id;
    private String kasatip;
    private String shortName;
    private String imgUrl;
    private String motortip;

    private Model model;
    private List<Motor> motorList;

    public static Kasa fromEntity(KasaEntity kasaEntity) {
        return Kasa.builder()
                .id(kasaEntity.getId())
                .kasatip(kasaEntity.getKasatip())
                .shortName(kasaEntity.getShortName())
                .imgUrl(kasaEntity.getImgUrl())
                .motortip(kasaEntity.getMotortip())
                .model(Model.fromEntity(kasaEntity.getModel()))
                .motorList(collectionAsStream(kasaEntity.getMotor()).map(Motor::fromEntity).toList())
                .build();
    }
}

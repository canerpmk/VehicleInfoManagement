package com.example.sahibinden.model;

import com.example.sahibinden.model.entity.MotorEntity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Motor {
    @Id
    private Long id;
    private String imgUrl;
    private String shortName;
    private String yil;
    private String motorName;

    private Kasa kasa;

    public static Motor fromEntity(MotorEntity motorEntity) {
        return Motor.builder()
                .id(motorEntity.getId())
                .imgUrl(motorEntity.getImgUrl())
                .shortName(motorEntity.getShortName())
                .kasa(Kasa.fromEntity(motorEntity.getKasa()))
                .yil(motorEntity.getYil())
                .motorName(motorEntity.getMotorName())
                .build();
    }


}

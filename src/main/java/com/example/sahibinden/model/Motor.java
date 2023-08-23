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
    private String shortName;
    private String yil;
    private String name;
    private Double guc;
    private Double hacim;
    private Double silindirsayisi;
    private Double tork;

    private Kasa kasa;

    public static Motor fromEntity(MotorEntity motorEntity) {
        return Motor.builder()
                .id(motorEntity.getId())
                .shortName(motorEntity.getShortName())
                .kasa(Kasa.fromEntity(motorEntity.getKasa()))
                .yil(motorEntity.getYil())
                .name(motorEntity.getName())
                .guc(motorEntity.getGuc())
                .hacim(motorEntity.getHacim())
                .silindirsayisi(motorEntity.getSilindirsayisi())
                .tork(motorEntity.getTork())
                .build();
    }


}

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
    private String name;
    private Double motorgucu;
    private Double motorhacmi;
    private Double silindirhacmi;
    private Double silindirsayisi;
    private Double tork;


    public static Motor fromEntity(MotorEntity motorEntity) {
        return Motor.builder()
                .id(motorEntity.getId())
                .motorgucu(motorEntity.getMotorgucu())
                .motorhacmi(motorEntity.getMotorhacmi())
                .silindirhacmi(motorEntity.getSilindirhacmi())
                .silindirsayisi(motorEntity.getSilindirsayisi())
                .tork(motorEntity.getTork())
                .build();
    }


}

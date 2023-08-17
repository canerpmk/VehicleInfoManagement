package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Motor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MotorResponse {
    private Long id;
    private Double motorgucu;
    private Double motorhacmi;
    private Double silindirhacmi;
    private Double silindirsayisi;
    private Double tork;

    public static MotorResponse fromModel(Motor motor) {
        return MotorResponse.builder()
                .id(motor.getId())
                .motorgucu(motor.getMotorgucu())
                .motorhacmi(motor.getMotorhacmi())
                .silindirhacmi(motor.getSilindirhacmi())
                .silindirsayisi(motor.getSilindirsayisi())
                .tork(motor.getTork())
                .build();
    }


}

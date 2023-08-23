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
    private String shortName;
    private String yil;
    private KasaResponse kasa;
    private String name;

    public static MotorResponse fromModel(Motor motor) {
        return MotorResponse.builder()
                .id(motor.getId())
                .shortName(motor.getShortName())
                .yil(motor.getYil())
                .name(motor.getName())
                .kasa(KasaResponse.fromModel(motor.getKasa()))
                .build();
    }


}

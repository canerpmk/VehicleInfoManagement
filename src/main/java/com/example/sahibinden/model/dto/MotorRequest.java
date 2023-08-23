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
public class MotorRequest {
    private String imgUrl;
    private String shortName;
    private String motorName;

    public Motor toModel() {
        return Motor.builder()
                .shortName(shortName)
                .imgUrl(imgUrl)
                .motorName(motorName)
                .build();
    }
}

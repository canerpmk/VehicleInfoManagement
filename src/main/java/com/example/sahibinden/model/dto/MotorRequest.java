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
    private Double motorgucu;
    private Double motorhacmi;
    private Double silindirhacmi;
    private Double silindirsayisi;
    private Double tork;

    public Motor toModel() {
        return Motor.builder()
                .motorgucu(motorgucu)
                .motorhacmi(motorhacmi)
                .silindirhacmi(silindirhacmi)
                .silindirsayisi(silindirsayisi)
                .tork(tork)
                .build();
    }
}

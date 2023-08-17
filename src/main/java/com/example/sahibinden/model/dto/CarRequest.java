package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {
    private Long id;
    private String name;
    private Long marka_id;
    private Long model_id;
    private Long motor_id;
    private Long paket_id;
    private Long ozellik_id;
    private Long kasa_id;

    public Car toModel() {
        return Car.builder()
                .id(id)
                .name(name)
                .marka(Marka.builder().id(marka_id).build())
                .model(Model.builder().id(model_id).build())
                .motor(Motor.builder().id(motor_id).build())
                .paket(Paket.builder().id(paket_id).build())
                .ozellik(Ozellik.builder().id(ozellik_id).build())
                .kasa(Kasa.builder().id(kasa_id).build())
                .build();
    }


}
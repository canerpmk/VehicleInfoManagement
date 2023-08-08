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
public class CarResponse {
    private Long id;
    private String name;
    private Marka marka;
    private Model model;
    private Motor motor;
    private Paket paket;
    private Ozellik ozellik;

    public static CarResponse fromModel(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .name(car.getName())
                .marka(car.getMarka())
                .model(car.getModel())
                .motor(car.getMotor())
                .paket(car.getPaket())
                .ozellik(car.getOzellik())
                .build();
    }

}
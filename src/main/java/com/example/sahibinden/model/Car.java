package com.example.sahibinden.model;

import com.example.sahibinden.model.dto.CarResponse;
import com.example.sahibinden.model.entity.CarEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    private Long id;
    private String name;
    private Marka marka;
    private Model model;
    private Motor motor;
    private Paket paket;
    private Ozellik ozellik;
    private Kasa kasa;


    public static Car fromEntity(CarEntity carEntity) {
        return Car.builder()
                .id(carEntity.getId())
                .name(carEntity.getName())
                .marka(carEntity.getMarka())
                .model(carEntity.getModel())
                .motor(carEntity.getMotor())
                .paket(carEntity.getPaket())
                .ozellik(carEntity.getOzellik())
                .kasa(carEntity.getKasa())
                .build();
    }




}
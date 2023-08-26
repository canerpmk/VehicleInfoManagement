package com.example.sahibinden.model;

import com.example.sahibinden.model.entity.CarEntity;
import jakarta.persistence.Id;
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
        return carEntity==null ? null:Car.builder()
                .id(carEntity.getId())
                .name(carEntity.getName())
                .marka(Marka.fromEntity(carEntity.getMarka()))
                .model(Model.fromEntity(carEntity.getModel()))
                .motor(Motor.fromEntity(carEntity.getMotor()))
                .paket(Paket.fromEntity(carEntity.getPaket()))
                .ozellik(Ozellik.fromEntity(carEntity.getOzellik()))
                .kasa(Kasa.fromEntity(carEntity.getKasa()))
                .build();
    }


}
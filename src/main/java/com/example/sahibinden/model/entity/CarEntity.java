package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Car;
import jakarta.persistence.*;
import lombok.*;


@ToString
@Builder
@EqualsAndHashCode(of = {"id"})
@Table(name = "Car")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private MarkaEntity marka;

    @ManyToOne
    private ModelEntity model;

    @ManyToOne
    private MotorEntity motor;

    @ManyToOne
    private OzellikEntity ozellik;

    @ManyToOne
    private PaketEntity paket;
    @ManyToOne
    private KasaEntity kasa;


    public static CarEntity fromModel(Car car) {

        return CarEntity.builder().id(car.getId())
                .name(car.getName())
                .marka(MarkaEntity.fromModel(car.getMarka()))
                .model(ModelEntity.fromModel(car.getModel()))
                .motor(MotorEntity.fromModel(car.getMotor()))
                .paket(PaketEntity.fromModel(car.getPaket()))
                .ozellik(OzellikEntity.fromModel(car.getOzellik()))
                .kasa(KasaEntity.fromModel(car.getKasa()))
                .build();

    }


}

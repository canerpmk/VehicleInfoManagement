package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Car;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@EqualsAndHashCode(of = {"id"})
@Table(name = "Car")
@Entity
@Getter
@Setter
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
        CarEntity carEntity = new CarEntity();
        carEntity.setId(car.getId());
        carEntity.setName(car.getName());
        carEntity.setMarka(MarkaEntity.fromModel(car.getMarka()));
        carEntity.setModel(ModelEntity.fromModel(car.getModel()));
        carEntity.setMotor(MotorEntity.fromModel(car.getMotor()));
        carEntity.setPaket(PaketEntity.fromModel(car.getPaket()));
        carEntity.setOzellik(OzellikEntity.fromModel(car.getOzellik()));
        carEntity.setKasa(KasaEntity.fromModel(car.getKasa()));
        return carEntity;
    }


}

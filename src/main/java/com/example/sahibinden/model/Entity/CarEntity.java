package com.example.sahibinden.model.entity;
import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.dto.CarResponse;
import jakarta.persistence.*;
import lombok.*;


@ToString
@EqualsAndHashCode(of = {"id"})
@Table(name = "Car")
@Entity
@Getter
@Setter
public class CarEntity extends Car {
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


    public static CarEntity fromModel(Car car){
        CarEntity carEntity = new CarEntity();
            carEntity.setId(car.getId());
            carEntity.setName(car.getName());
            carEntity.setMarka(car.getMarka());
            carEntity.setModel(car.getModel());
            carEntity.setMotor(car.getMotor());
            carEntity.setPaket(car.getPaket());
            carEntity.setOzellik(car.getOzellik());
            return carEntity;
    }






}

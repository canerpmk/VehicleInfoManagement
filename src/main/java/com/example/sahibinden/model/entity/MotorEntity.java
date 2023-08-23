package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Motor;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Motor")
public class MotorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double guc;
    private Double hacim;
    private Double silindirsayisi;
    private Double tork;
    private String name;
    private String yil;
    @Column(unique = true)
    private String shortName;


    @ManyToOne
    private KasaEntity kasa;

    public static MotorEntity fromModel(Motor motor) {
        MotorEntity motorEntity = new MotorEntity();
        motorEntity.setId(motor.getId());
        motorEntity.setGuc(motor.getGuc());
        motorEntity.setHacim(motor.getHacim());
        motorEntity.setSilindirsayisi(motor.getSilindirsayisi());
        motorEntity.setTork(motor.getTork());
        motorEntity.setName(motor.getName());
        motorEntity.setYil(motor.getYil());
        motorEntity.setShortName(motor.getShortName());
        motorEntity.setKasa(KasaEntity.fromModel(motor.getKasa()));
        return motorEntity;
    }


}

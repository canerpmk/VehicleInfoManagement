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
    private Double motorgucu;
    private Double motorhacmi;
    private Double silindirhacmi;
    private Double silindirsayisi;
    private Double tork;


    public static MotorEntity fromModel(Motor motor) {
        MotorEntity motorEntity = new MotorEntity();
        motorEntity.setId(motor.getId());
        motorEntity.setMotorgucu(motor.getMotorgucu());
        motorEntity.setMotorhacmi(motor.getMotorhacmi());
        motorEntity.setSilindirhacmi(motor.getSilindirhacmi());
        motorEntity.setSilindirsayisi(motor.getSilindirsayisi());
        motorEntity.setTork(motor.getTork());
        return motorEntity;
    }


}

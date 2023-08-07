package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Motor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Motor")
public class MotorEntity extends Motor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private Double motorgucu;
    @Column
    private Double motorhacmi;
    @Column
    private Double silindirhacmi;
    @Column
    private Double silindirsayisi;
    @Column
    private Double tork;

}

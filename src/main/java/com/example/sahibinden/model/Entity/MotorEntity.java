package com.example.sahibinden.model.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MotorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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

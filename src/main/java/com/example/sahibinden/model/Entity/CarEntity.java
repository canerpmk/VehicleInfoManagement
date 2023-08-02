package com.example.sahibinden.model.Entity;

import com.example.sahibinden.model.Ozellik;
import jakarta.persistence.*;
import lombok.*;


@ToString
@EqualsAndHashCode(of = {"id"})
@Table(name = "Car")
@Entity
@Data
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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








}

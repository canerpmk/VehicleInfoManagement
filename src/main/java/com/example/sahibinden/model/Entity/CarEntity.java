package com.example.sahibinden.model.entity;
import jakarta.persistence.*;
import lombok.*;


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

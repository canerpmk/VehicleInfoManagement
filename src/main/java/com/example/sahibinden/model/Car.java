package com.example.sahibinden.model;

import jakarta.persistence.*;
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
    private int id;
    private Marka marka;
    private int model_id;
    private int motor_id;
    private int ozellik;
    private int paket;




}

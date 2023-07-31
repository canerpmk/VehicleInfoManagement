
package com.example.sahibinden.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class CarMotor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int motor_id;
    private int motorgucu;
    private double motorhacmi;
    private double silindirhacmi;
    private double silindirsayisi;
    private double tork;

}

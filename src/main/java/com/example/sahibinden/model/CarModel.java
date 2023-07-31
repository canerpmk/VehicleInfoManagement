
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
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int model_id;
    private String model;



}

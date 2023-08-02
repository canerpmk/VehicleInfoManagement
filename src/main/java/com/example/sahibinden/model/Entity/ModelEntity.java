package com.example.sahibinden.model.Entity;

import com.example.sahibinden.model.Marka;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;


}

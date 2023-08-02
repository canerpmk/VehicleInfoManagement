package com.example.sahibinden.model.Entity;

import com.example.sahibinden.model.Model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PaketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String paket;



}

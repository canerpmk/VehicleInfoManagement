
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
public class CarPacket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int packet_id;
    private String packet;



}

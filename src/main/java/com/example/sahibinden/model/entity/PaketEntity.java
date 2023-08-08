package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Model;
import com.example.sahibinden.model.Paket;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Paket")
public class PaketEntity extends Paket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paket;

    public static PaketEntity fromModel(Paket paket){
        PaketEntity paketEntity = new PaketEntity();
        paketEntity.setId(paket.getId());
        paketEntity.setPaket(paket.getPaket());
        return paketEntity;
    }

}

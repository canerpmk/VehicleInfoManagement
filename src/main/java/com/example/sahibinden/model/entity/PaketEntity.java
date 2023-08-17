package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Paket;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Paket")
public class PaketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paket;

    public static PaketEntity fromModel(Paket paket) {
        PaketEntity paketEntity = new PaketEntity();
        paketEntity.setId(paket.getId());
        paketEntity.setPaket(paket.getPaket());
        return paketEntity;
    }


}

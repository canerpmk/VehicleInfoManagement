package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Paket;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Paket")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paket;

    public static PaketEntity fromModel(Paket paket) {
        if (paket==null){
            return null;
        }
       return PaketEntity.builder()
               .id(paket.getId())
               .paket(paket.getPaket())
               .build();
    }


}

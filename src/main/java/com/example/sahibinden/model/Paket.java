package com.example.sahibinden.model;

import com.example.sahibinden.model.entity.PaketEntity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paket {
    @Id
    private Long id;
    private String paket;

    public static Paket fromEntity(PaketEntity paketEntity) {
        return Paket.builder()
                .id(paketEntity.getId())
                .paket(paketEntity.getPaket())
                .build();
    }

}

package com.example.sahibinden.model;

import com.example.sahibinden.model.entity.KasaEntity;
import com.example.sahibinden.model.entity.MarkaEntity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Kasa {
    @Id
    private Long id;
    private String tip;
    private Model model;
    private Marka marka;
    private String imgUrl;
    private String ozellik;
    private String motortip;

    public static Kasa fromEntity(KasaEntity kasaEntity) {
        return Kasa.builder()
                .id(kasaEntity.getId())
                .tip(kasaEntity.getTip())
                .build();
    }
}

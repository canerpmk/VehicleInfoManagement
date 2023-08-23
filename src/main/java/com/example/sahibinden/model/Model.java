package com.example.sahibinden.model;


import com.example.sahibinden.model.entity.ModelEntity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.example.sahibinden.common.Utils.collectionAsStream;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    @Id
    private Long id;
    private String name;
    private String shortName;
    private List<Kasa> kasaList;
    private Marka marka;

    public static Model fromEntity(ModelEntity modelEntity) {
        return Model.builder()
                .id(modelEntity.getId())
                .name(modelEntity.getName())
                .shortName(modelEntity.getShortName())
                .marka(Marka.fromEntity(modelEntity.getMarka()))
                .kasaList(collectionAsStream(modelEntity.getKasa()).map(Kasa::fromEntity).toList())
                .build();
    }


}

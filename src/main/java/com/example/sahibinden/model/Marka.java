package com.example.sahibinden.model;

import com.example.sahibinden.model.entity.MarkaEntity;
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
public class Marka {
    @Id
    private Long id;
    private String name;
    private String shortName;
    private String imgUrl;
    private String info;
    private List<Model> modelList;

    public static Marka fromEntity(MarkaEntity markaEntity) {
        return Marka.builder()
                .id(markaEntity.getId())
                .name(markaEntity.getName())
                .shortName(markaEntity.getShortName())
                .imgUrl(markaEntity.getImgUrl())
                .info(markaEntity.getInfo())
                .modelList(collectionAsStream(markaEntity.getModel()).map(Model::fromEntity).toList())
                .build();
    }


}
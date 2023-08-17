package com.example.sahibinden.model;


import com.example.sahibinden.model.entity.ModelEntity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    @Id
    private Long id;
    private String name;
    private Marka marka;
    private String shortName;
    private String url;


    public static Model fromEntity(ModelEntity modelEntity) {
        return Model.builder()
                .id(modelEntity.getId())
                .name(modelEntity.getName())
                .shortName(modelEntity.getShortName())
                .marka(Marka.fromEntity(modelEntity.getMarka()))
                .build();
    }


}

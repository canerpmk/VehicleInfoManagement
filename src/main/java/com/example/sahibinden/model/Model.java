
package com.example.sahibinden.model;


import com.example.sahibinden.model.entity.MarkaEntity;
import com.example.sahibinden.model.entity.ModelEntity;
import jakarta.persistence.*;
import lombok.*;


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
                .marka(modelEntity.getMarka())
                .build();
    }


}

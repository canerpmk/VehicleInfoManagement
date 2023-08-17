package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelRequest {
    private String name;
    private Long marka_id;

    public Model toModel() {
        return Model.builder()
                .name(name)
                .marka(Marka.builder().id(marka_id).build())
                .build();
    }


}

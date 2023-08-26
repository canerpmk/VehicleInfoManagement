package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Marka;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkaResponse {
    private Long id;
    private String name;
    private String shortName;
    private String imgUrl;
    private String info;

    private List<ModelResponse> modelList;


    public static MarkaResponse fromModel(Marka marka) {
        return marka==null ? null :MarkaResponse.builder()
                .id(marka.getId())
                .name(marka.getName())
                .shortName(marka.getShortName())
                .imgUrl(marka.getImgUrl())
                .info(marka.getInfo())
                .modelList(marka.getModelList().stream().map(ModelResponse::fromModel).toList())
                .build();
    }

}
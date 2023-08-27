package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Marka;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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


//    public static MarkaResponse fromModel(Marka marka) {
//        return marka==null ? null :MarkaResponse.builder()
//                .id(marka.getId())
//                .name(marka.getName())
//                .shortName(marka.getShortName())
//                .imgUrl(marka.getImgUrl())
//                .info(marka.getInfo())
//                .modelList(marka.getModelList().stream().map(ModelResponse::fromModel).toList())
//                .build();
//    }
public static MarkaResponse fromModel(Marka marka) {
    if (marka == null) {
        return null;
    }

    MarkaResponse.MarkaResponseBuilder markaResponseBuilder = MarkaResponse.builder()
            .id(marka.getId())
            .name(marka.getName())
            .shortName(marka.getShortName())
            .imgUrl(marka.getImgUrl())
            .info(marka.getInfo());

    if (marka.getModelList() != null) {
        List<ModelResponse> modelResponses = marka.getModelList().stream()
                .map(ModelResponse::fromModel)
                .collect(Collectors.toList());
        markaResponseBuilder.modelList(modelResponses);
    }

    return markaResponseBuilder.build();
}

}
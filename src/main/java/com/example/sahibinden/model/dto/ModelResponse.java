package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Model;
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
public class ModelResponse {
    private Long id;
    private String name;
    private String shortName;
    private MarkaResponse marka;
    private List<KasaResponse> kasaList;

//    public static ModelResponse fromModel(Model model) {
//        return model==null ? null :ModelResponse.builder()
//                .id(model.getId())
//                .name(model.getName())
//                .shortName(model.getShortName())
//                .kasaList(model.getKasaList().stream().map(KasaResponse::fromModel).toList())
//                .marka(MarkaResponse.fromModel(model.getMarka()))
//                .build();
//    }
public static ModelResponse fromModel(Model model) {
    if (model == null) {
        return null;
    }

    ModelResponse.ModelResponseBuilder modelResponseBuilder = ModelResponse.builder()
            .id(model.getId())
            .name(model.getName())
            .shortName(model.getShortName())
            .marka(MarkaResponse.fromModel(model.getMarka()));

    if (model.getKasaList() != null) {
        List<KasaResponse> kasaResponses = model.getKasaList().stream()
                .map(KasaResponse::fromModel)
                .collect(Collectors.toList());
        modelResponseBuilder.kasaList(kasaResponses);
    }

    return modelResponseBuilder.build();
}


}

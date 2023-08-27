package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Kasa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KasaResponse {
    private Long id;
    private String yil;
    private String kasatip;
    private String shortName;
    private String imgUrl;
    private String motortip;
    private List<MotorResponse> motorList;

//    public static KasaResponse fromModel(Kasa kasa) {
//        return kasa==null ? null :KasaResponse.builder()
//                .id(kasa.getId())
//                .yil(kasa.getYil())
//                .kasatip(kasa.getKasaTip())
//                .imgUrl(kasa.getImgUrl())
//                .motortip(kasa.getMotorTip())
//                .shortName(kasa.getShortName())
//                .motorList(kasa.getMotorList().stream().map(MotorResponse::fromModel).toList())
//                .build();
//    }

    public static KasaResponse fromModel(Kasa kasa) {
        if (kasa == null) {
            return null;
        }

        KasaResponse.KasaResponseBuilder kasaResponseBuilder = KasaResponse.builder()
                .id(kasa.getId())
                .yil(kasa.getYil())
                .kasatip(kasa.getKasaTip())
                .imgUrl(kasa.getImgUrl())
                .motortip(kasa.getMotorTip())
                .shortName(kasa.getShortName());

        if (kasa.getMotorList() != null) {
            List<MotorResponse> motorResponses = kasa.getMotorList().stream()
                    .map(MotorResponse::fromModel)
                    .collect(Collectors.toList());
            kasaResponseBuilder.motorList(motorResponses);
        }

        return kasaResponseBuilder.build();
    }




//    public static KasaResponse fromModel(Kasa kasa) {
//        if (kasa == null) {
//            return null;
//        }
//
//        KasaResponse kasaResponse = new KasaResponse();
//        kasaResponse.setId(kasa.getId());
//        // Diğer alanları set etme işlemleri...
//
//        List<MotorResponse> motorResponses = new ArrayList<>();
//        if (kasa.getMotorList() != null) {
//            motorResponses = kasa.getMotorList().stream().map(MotorResponse::fromModel).collect(Collectors.toList());
//        }
//        kasaResponse.setMotorList(motorResponses);
//
//        return kasaResponse;
//    }
}

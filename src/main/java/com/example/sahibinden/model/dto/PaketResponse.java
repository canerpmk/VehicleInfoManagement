package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Paket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaketResponse {
    private Long id;
    private String paket;

    public static PaketResponse fromModel(Paket paket) {
        return PaketResponse.builder()
                .id(paket.getId())
                .paket(paket.getPaket())
                .build();
    }


}

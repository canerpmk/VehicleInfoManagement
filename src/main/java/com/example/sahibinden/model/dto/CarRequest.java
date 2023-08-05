package com.example.sahibinden.model.dto;

import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.entity.CarEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {
    private Long id;
    private String name;

    public Car toModel() {
        return Car.builder()
                .id(id)
                .name(name)
                .build();
    }

}

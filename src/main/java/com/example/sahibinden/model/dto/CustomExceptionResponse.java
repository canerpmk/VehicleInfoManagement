package com.example.sahibinden.model.dto;

import com.example.sahibinden.exception.model.CustomException;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class CustomExceptionResponse {
    private String message;

    public static CustomExceptionResponse fromModel(CustomException customException) {
        return CustomExceptionResponse.builder()
                .message(customException.getMessage())
                .build();
    }
}
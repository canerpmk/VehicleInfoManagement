package com.example.sahibinden.exception;

import com.example.sahibinden.exception.model.CustomException;
import com.example.sahibinden.model.dto.CustomExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)

    public ResponseEntity<CustomExceptionResponse> handleCustomException(CustomException ex) {
        return ResponseEntity.status(ex.getStatus()).body(CustomExceptionResponse.fromModel(ex));
    }

}


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
    public CustomExceptionResponse handleCustomException(CustomException ex) {
        return CustomExceptionResponse.fromModel(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        return new ResponseEntity<>("Bir  hata oluştu."+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

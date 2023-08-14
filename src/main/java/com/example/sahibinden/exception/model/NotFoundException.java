package com.example.sahibinden.exception.model;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException(Long id)  {
        super(HttpStatus.NOT_FOUND,  "Girdiğiniz id bulunamadı: " +id);
    }

    public NotFoundException(HttpStatus notfound, String message) {
        super(notfound,message);
    }
}

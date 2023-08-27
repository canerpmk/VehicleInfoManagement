package com.example.sahibinden.exception.model;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotFoundExceptionTest {
    @Test
    void testConstructorWithMessage() {
        String expectedMessage = "Resource not found";

        NotFoundException notFoundException = new NotFoundException(expectedMessage);

        assertEquals(HttpStatus.NOT_FOUND, notFoundException.getStatus());
        assertEquals(expectedMessage, notFoundException.getMessage());
    }

    @Test
    void testConstructorWithId() {
        Long id = 123L;
        String expectedMessage = "Girdiğiniz id bulunamadı: " + id;

        NotFoundException notFoundException = new NotFoundException(id);

        assertEquals(HttpStatus.NOT_FOUND, notFoundException.getStatus());
        assertEquals(expectedMessage, notFoundException.getMessage());
    }

    @Test
    void testConstructorWithCustomStatusAndMessage() {
        HttpStatus customStatus = HttpStatus.BAD_REQUEST;
        String customMessage = "Custom error message";

        NotFoundException notFoundException = new NotFoundException(customStatus, customMessage);

        assertEquals(customStatus, notFoundException.getStatus());
        assertEquals(customMessage, notFoundException.getMessage());
    }
}

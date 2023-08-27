package com.example.sahibinden.exception.model;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CustomExceptionTest {
    @Test
    void testConstructorAndGetters() {
        HttpStatus expectedStatus = HttpStatus.NOT_FOUND;
        String expectedMessage = "Resource not found";

        CustomException customException = new CustomException(expectedStatus, expectedMessage);

        assertEquals(expectedStatus, customException.getStatus());
        assertEquals(expectedMessage, customException.getMessage());
    }
}

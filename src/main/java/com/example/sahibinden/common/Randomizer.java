package com.example.sahibinden.common;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Randomizer {

    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
    public static final int DEFAULT_LENGTH = 5;

    public String generate() {
        return generate(DEFAULT_LENGTH);
    }

    public String generate(Integer length) {
        length = length == null || length<DEFAULT_LENGTH ? DEFAULT_LENGTH : length;
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int rnd = random.nextInt(CHARACTERS.length());
            char s = CHARACTERS.charAt(rnd);
            randomString.append(s);
        }
        return randomString.toString();
    }
}

package com.example.sahibinden.utils;

import com.example.sahibinden.model.Motor;

import java.util.concurrent.ThreadLocalRandom;

public class TestUtils {

    public static Motor motorBuilder() {
        return motorBuilder(ThreadLocalRandom.current().nextLong());
    }

    public static Motor motorBuilder(Long id) {
        return Motor.builder().id(id).build();
    }
}

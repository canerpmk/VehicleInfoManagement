package com.example.sahibinden.service;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Model;
import com.example.sahibinden.model.Motor;

import java.util.List;

public interface MotorService {
    List<Motor> getAllMotor();

    Motor getMotorById(Long id);

    Motor addMotor(Motor motor);

    Motor updateMotor(Motor updatedMotor);

    boolean deleteMotorById(Long id);

    List<String> parseWebPage(String url);
}

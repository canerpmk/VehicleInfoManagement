package com.example.sahibinden.service;

import com.example.sahibinden.model.Motor;

import java.util.List;

public interface MotorService {
    List<Motor> getAllMotor();

    Motor getMotorById(Long id);

    Motor addMotor(Motor motor);

    List<Motor> addMotors(List<Motor> motorList);

    Motor updateMotor(Motor updatedMotor);

    boolean deleteMotorById(Long id);

    Motor getKMotorByShortName(String shortName);


}

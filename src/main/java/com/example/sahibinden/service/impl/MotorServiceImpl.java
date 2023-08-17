package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Motor;
import com.example.sahibinden.model.entity.MotorEntity;
import com.example.sahibinden.repository.MotorRepository;
import com.example.sahibinden.service.MotorService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MotorServiceImpl implements MotorService {
    private final MotorRepository motorRepository;

    public Motor getMotorById(Long id) {
        MotorEntity motorEntity = motorRepository.findById(id).orElseThrow();
        return Motor.fromEntity(motorEntity);
    }

    public List<Motor> getAllMotor() {
        List<MotorEntity> motorEntities = motorRepository.findAll();
        return motorEntities.stream()
                .map(Motor::fromEntity)
                .collect(Collectors.toList());
    }

    public Motor addMotor(Motor motor) {
        MotorEntity motorEntity = MotorEntity.fromModel(motor);
        MotorEntity addedMotorEntity = motorRepository.save(motorEntity);
        return Motor.fromEntity(addedMotorEntity);
    }

    public List<String> parseWebPage(String url) {
        List<String> parsedDataList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(url).get();
            Element seriallist = document.getElementsByClass("seriallist").first();

            for (Element link : seriallist.children()) {
                String linkText = link.text();
                String linkHref = link.attr("href");
                parsedDataList.add("Text: " + linkText + ", URL: " + linkHref);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parsedDataList;
    }

    public Motor updateMotor(Motor motor) {
        if (motorRepository.existsById(motor.getId())) {
            MotorEntity updatedMarkaEntity = motorRepository.save(MotorEntity.fromModel(motor));
            return Motor.fromEntity(updatedMarkaEntity);
        }
        return null;
    }

    public boolean deleteMotorById(Long id) {
        motorRepository.deleteById(id);
        return !motorRepository.existsById(id);
    }
}

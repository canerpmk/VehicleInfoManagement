package com.example.sahibinden.service.impl;

import com.example.sahibinden.exception.model.CustomException;
import com.example.sahibinden.model.Car;
import com.example.sahibinden.model.entity.*;
import com.example.sahibinden.repository.*;
import com.example.sahibinden.service.CarService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final MarkaRepository markaService;
    private final ModelRepository modelService;
    private final MotorRepository motorService;
    private final OzellikRepository ozellikService;
    private final PaketRepository paketService;

    public Car getCarById(Long id) {
        CarEntity carEntity = carRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "girdiği" + id));
        return CarEntity.fromEntity(carEntity);
    }

    public List<Car> getAllCars() {
        List<CarEntity> carEntities = carRepository.findAll();
        return carEntities.stream()
                .map(Car::fromEntity)
                .collect(Collectors.toList());
    }

    public Car addCar(Car car) {
        MarkaEntity marka = markaService.findById(car.getMarka().getId()).get();
        ModelEntity model = modelService.findById(car.getModel().getId()).get();
        MotorEntity motor = motorService.findById(car.getMotor().getId()).get();
        OzellikEntity ozellik = ozellikService.findById(car.getModel().getId()).get();
        PaketEntity paket = paketService.findById(car.getModel().getId()).get();
        CarEntity carEntity = CarEntity.fromModel(car);
        carEntity.setMarka(marka);
        carEntity.setModel(model);
        carEntity.setMotor(motor);
        carEntity.setOzellik(ozellik);
        carEntity.setPaket(paket);
        CarEntity addedCarEntity = carRepository.save(carEntity);

        return Car.fromEntity(addedCarEntity);

    }
    public String parseWebPage(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Element seriallist = document.getElementsByClass("seriallist").first();
            //seriallist.get(0).children()

            StringBuilder response = new StringBuilder();
            for (Element link : seriallist.children()) {
                String linkText = link.text();
                String linkHref = link.attr("href");
                response.append("Text: ").append(linkText).append(", URL: ").append(linkHref).append("\n");
            }

            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while parsing the web page.";
        }
    }
    public Car updateCar(Car car) {
        if (carRepository.existsById(car.getId())) {
            CarEntity updatedCarEntity = carRepository.save(CarEntity.fromModel(car));
            return Car.fromEntity(updatedCarEntity);
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + car.getId());
    }

    public boolean deleteCarById(Long id) {
        carRepository.deleteById(id);
        return !carRepository.existsById(id);


    }
}
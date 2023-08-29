package com.example.sahibinden.utils;

import com.example.sahibinden.model.*;
import com.example.sahibinden.model.entity.*;

import java.util.concurrent.ThreadLocalRandom;

public class TestUtils {

    public static Motor motorBuilder() {
        return motorBuilder(ThreadLocalRandom.current().nextLong());
    }

    public static Motor motorBuilder(Long id) {
        return Motor.builder().id(id).build();
    }

    public static Car carBuilder() {
        return carBuilder(ThreadLocalRandom.current().nextLong());
    }

    public static Car carBuilder(Long id) {
        return Car.builder().id(id).build();
    }

    public static Model modelBuilder(Long id) {
        return Model.builder().id(id).build();
    }

    public static Model modelBuilder() {
        return modelBuilder(ThreadLocalRandom.current().nextLong());
    }

    public static Marka markaBuilder(Long id) {
        return Marka.builder().id(id).build();
    }

    public static Marka markaBuilder() {
        return markaBuilder(ThreadLocalRandom.current().nextLong());
    }

    public static Kasa kasaBuilder(Long id) {
        return Kasa.builder().id(id).build();
    }

    public static Kasa kasaBuilder() {
        return kasaBuilder(ThreadLocalRandom.current().nextLong());
    }


    public static Ozellik ozellikBuilder(Long id) {
        return Ozellik.builder().id(id).build();
    }

    public static Ozellik ozellikBuilder() {
        return ozellikBuilder(ThreadLocalRandom.current().nextLong());
    }

    public static Paket paketBuilder(Long id) {
        return Paket.builder().id(id).build();
    }

    public static Paket paketBuilder() {
        return paketBuilder(ThreadLocalRandom.current().nextLong());
    }


    public static MotorEntity motorEntity() {
        return motorEntity(ThreadLocalRandom.current().nextLong());
    }

    public static MotorEntity motorEntity(Long id) {
        return MotorEntity.builder()
                .id(id)
                .build();
    }

    public static MotorEntity motorEntity(Long id, String shortName) {
        return MotorEntity.builder()
                .id(id)
                .shortName(shortName)
                .build();
    }


    public static MarkaEntity markaEntity() {

        return markaEntity(ThreadLocalRandom.current().nextLong());
    }

    public static MarkaEntity markaEntity(Long id) {
        return MarkaEntity.builder()
                .id(id)
                .build();
    }

    public static MarkaEntity markaEntity(Long id, String name) {

        return MarkaEntity.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static MarkaEntity markaEntityShortname(Long id, String name) {

        return MarkaEntity.builder()
                .id(id)
                .name(name)
                .build();
    }


    public static Marka marka(Long id, String shortName) {

        return Marka.builder()
                .id(id)
                .shortName(shortName)
                .build();
    }


    public static ModelEntity modelEntity() {
        return modelEntity(ThreadLocalRandom.current().nextLong());
    }

    public static ModelEntity modelEntity(Long id) {
        return ModelEntity.builder()
                .id(id)
                .build();
    }

    public static ModelEntity modelEntity(Long id, String shortName) {
        return ModelEntity.builder()
                .id(id)
                .shortName(shortName)
                .build();
    }

    public static Model model(Long id, String shortName) {
        return Model.builder()
                .id(id)
                .shortName(shortName)
                .build();
    }

    public static Model model(Long id, String name, Marka marka, Long markaId) {
        return Model.builder()
                .id(id)
                .name(name)
                .marka(marka)
                .marka(Marka.builder().id(markaId).build())
                .build();
    }


    public static String mockMarkaPath() {
        String markaPath = "mockMarkaPath";
        return markaPath;
    }

    public static String mockModelPath() {
        String modelPath = "mockModelPath";
        return modelPath;
    }

    public static String mockKasaPath() {
        String kasaPath = "mockModelPath";
        return kasaPath;
    }


    public static PaketEntity paketEntity() {
        return paketEntity(ThreadLocalRandom.current().nextLong());
    }

    public static PaketEntity paketEntity(Long id) {
        return PaketEntity.builder()
                .id(id)
                .build();
    }


    public static CarEntity carEntity() {
        return carEntity(ThreadLocalRandom.current().nextLong());
    }

    public static CarEntity carEntity(Long id) {
        return CarEntity.builder()
                .id(id)
                .build();
    }

    public static CarEntity carEntity(Long id, String name) {
        return CarEntity.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static Car car() {
        Long id = ThreadLocalRandom.current().nextLong();
        return Car.builder()
                .id(id)
                .marka(Marka.builder().id(1L).build())
                .model(Model.builder().id(2L).build())
                .motor(Motor.builder().id(3L).build())
                .ozellik(Ozellik.builder().id(4L).build())
                .paket(Paket.builder().id(5L).build())
                .kasa(Kasa.builder().id(6L).build())
                .build();
    }

    public static Car car(Long id, String name) {

        return Car.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static Long randomId() {
        Long deleteId = ThreadLocalRandom.current().nextLong();

        return deleteId;
    }


    public static OzellikEntity ozellikEntity() {
        return ozellikEntity(ThreadLocalRandom.current().nextLong());
    }

    public static OzellikEntity ozellikEntity(Long id) {
        return OzellikEntity.builder()
                .id(id)
                .build();
    }


    public static KasaEntity kasaEntity() {
        return kasaEntity(ThreadLocalRandom.current().nextLong());
    }

    public static KasaEntity kasaEntity(Long id) {
        return KasaEntity.builder()
                .id(id)
                .build();
    }

    public static KasaEntity kasaEntity(Long id, String shortName) {
        return KasaEntity.builder()
                .id(id)
                .shortName(shortName)
                .build();
    }

    public static KasaEntity kasaEntity(Long id, ModelEntity model) {
        return KasaEntity.builder()
                .id(id)
                .model(model)
                .build();
    }

    public static Kasa kasa(Model model) {
        return Kasa.builder()
                .model(model)
                .build();
    }


}

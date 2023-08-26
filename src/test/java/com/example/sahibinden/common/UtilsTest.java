package com.example.sahibinden.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class UtilsTest {
    @Test
    public void testParseValueWithUnitValidInput() {
        String valueStr = "5.25 kg";
        double expectedResult = 5.25;

        double result = Utils.parseValueWithUnit(valueStr);

        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testParseValueWithUnitInvalidInput() {
        String valueStr = "invalid";
        double expectedResult = 0.0;

        double result = Utils.parseValueWithUnit(valueStr);

        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testParseValueWithUnitEmptyInput() {
        String valueStr = "";
        double expectedResult = 0.0;

        double result = Utils.parseValueWithUnit(valueStr);

        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void testCollectionAsStreamWithNullCollection() {
        Collection<String> nullCollection = null;
        Stream<String> resultStream = Utils.collectionAsStream(nullCollection);

        assertEquals(0, resultStream.count());
    }

    @Test
    public void testCollectionAsStreamWithEmptyCollection() {
        Collection<Integer> emptyCollection = new ArrayList<>();
        Stream<Integer> resultStream = Utils.collectionAsStream(emptyCollection);

        assertEquals(0, resultStream.count());
    }

    @Test
    public void testCollectionAsStreamWithNonEmptyCollection() {
        List<String> nonEmptyList = Arrays.asList("apple", "banana", "cherry");
        Stream<String> resultStream = Utils.collectionAsStream(nonEmptyList);

        List<String> resultList = resultStream.collect(Collectors.toList());

        assertEquals(nonEmptyList, resultList);
    }

}

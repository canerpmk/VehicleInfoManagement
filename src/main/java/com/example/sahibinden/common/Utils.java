package com.example.sahibinden.common;

import java.util.Collection;
import java.util.stream.Stream;

public class Utils {
    public static <T> Stream<T> collectionAsStream(Collection<T> collection) {
        return collection == null ? Stream.empty() : collection.stream();
    }

    public static double parseValueWithUnit(String valueStr) {
        try {
            String numericValue = valueStr.replaceAll("[^0-9.]", "");
            return Double.parseDouble(numericValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }


}
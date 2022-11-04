package com.deepspace.deanery;

import com.deepspace.deanery.exception.DeaneryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class RandomUtils {

    private static final Random RANDOM = new Random();

    public RandomUtils() {
        throw new DeaneryException(DeaneryException.Reason.NOT_IMPLEMENTED, "Утильный класс");
    }

    public static <T> T getRandomDictionaryItem(List<T> dictionary) {
        return dictionary.get(RANDOM.nextInt(dictionary.size()));
    }

    public static <T> List<T> getRandomDictionaryItems(List<T> dictionary, int size) {
        List<T> resultList = new ArrayList<>(size);
        for (int i=0; i<size; i++) {
            T item = dictionary.get(RANDOM.nextInt(dictionary.size()));
            resultList.add(item);
        }
        return resultList;
    }
}

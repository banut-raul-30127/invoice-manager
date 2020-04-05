package com.nexttech.service;

import java.util.Random;

public class GenerateName {

    //    private final static String[] LIST_OF_FIRST_NAMES = {"1", "2", "3"};
    //    private final static String[] LIST_OF_MIDDLE_NAMES = {"a", "b", "c"};
    //    private final static String[] LIST_OF_LAST_NAMES = {"@", "#"};
    private final static String[] LIST_OF_FIRST_NAMES = {"Romanian", "Belgian", "German"};
    private final static String[] LIST_OF_MIDDLE_NAMES = {"Food", "Electricity", "Delivery"};
    private final static String[] LIST_OF_LAST_NAMES = {"Incorporated", "Limited"};

    private Random random = new Random();

    public int getRandomNumber(int value) {
        return random.nextInt(value);
    }

    public String getRandomFirstName() {
        return LIST_OF_FIRST_NAMES[getRandomNumber(3)];
    }

    public String getRandomMiddleName() {
        return LIST_OF_MIDDLE_NAMES[getRandomNumber(3)];
    }

    public String getRandomLastName() {
        return LIST_OF_LAST_NAMES[getRandomNumber(2)];
    }

    public String get5RandomAlphabeticalChar() {
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            newString.append((char) (random.nextInt(26) + 'a'));
        }
        return newString.toString();
    }
}

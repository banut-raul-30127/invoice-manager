package com.raul.service;

import java.util.Random;

public class GeneratePhoneNumber {

    private Random random = new Random();

    public String getRandomPhoneNumber() {
        String phoneNumber = "";
        for (int i = 0; i < 14; i++) {
            phoneNumber = phoneNumber + String.valueOf(random.nextInt(10));
        }
        return phoneNumber;
    }
}

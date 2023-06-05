package com.ceo.publicservices.domain.validation;

import java.util.Random;

public class RandomNumberGenerator {
    static Random random = new Random();
    static int randomNumber = random.nextInt(900000000) + 100000000;

    public static String getAccountNumber() {
        return  String.valueOf(randomNumber);

    }

}

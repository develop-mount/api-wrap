package com.seelyn.apiwrap.utils;

import java.util.Random;

public final class NonceUtils {

    private NonceUtils() {

    }

    private static Random random = new Random();

    public static Integer nonce() {
        return random.nextInt();
    }

}

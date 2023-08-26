package icu.develop.apiwrap.utils;

import java.util.Random;


/**
 * 随机数生成工具
 * @author linfeng
 */
public final class NonceUtils {

    private NonceUtils() {

    }

    private static Random random = new Random();

    public static Integer nonce() {
        return random.nextInt();
    }

}

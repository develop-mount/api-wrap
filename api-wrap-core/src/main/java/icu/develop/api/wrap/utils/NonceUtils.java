package icu.develop.api.wrap.utils;

import java.util.Random;


/**
 * 随机数生成工具
 * @author linfeng
 */
public final class NonceUtils {

    private NonceUtils() {

    }

    private static final Random random = new Random();

    public static Integer nonce() {
        return random.nextInt();
    }

}

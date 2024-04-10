package co.istad.ite2mbanking.util;

import java.util.Random;
public class RandomUtil {

    public static String generate9Digits() {
        Random random = new Random();
        return String.format("%09d", random.nextInt(1000000000));
    }

}


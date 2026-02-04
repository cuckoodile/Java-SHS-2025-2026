package util;

import java.util.Random;

public class RandomUtil {
    private static final Random rand = new Random();

    public static int range(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }
}

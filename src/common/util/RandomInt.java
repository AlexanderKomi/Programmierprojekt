package common.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomInt {

    /**
     * Return a random int between min and max (both bounds inclusive)
     * @param min lower bound
     * @param max higher bound
     */
    public static int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}

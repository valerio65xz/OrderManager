package com.ee.ordermanager.random;

import org.jeasy.random.EasyRandom;

public class Randomize {

    public static <T> T random(Class<T> randomClass) {
        return new EasyRandom().nextObject(randomClass);
    }

}
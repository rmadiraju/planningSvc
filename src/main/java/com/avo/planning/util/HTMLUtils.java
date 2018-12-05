package com.avo.planning.util;

import java.util.Random;

public class HTMLUtils {

    public static String getRandomHexColour() {
        final Random random = new Random();
        final String[] letters = "0123456789ABCDEF".split("");
        String color = "#";
        for (int i = 0; i < 6; i++) {
            color += letters[Math.round(random.nextFloat() * 15)];
        }
        return color;
    }
}

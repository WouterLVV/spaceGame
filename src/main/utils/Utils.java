package main.utils;

import main.world.Body;

import java.util.Random;

public class Utils {
    private static final char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] numbers = "0123456789".toCharArray();
    private static Random random = new Random();

    public static final long NANODIVIDINGFACTOR = 1000000000L;
    public static final long MICRODIVIDINGFACTOR = 1000000L;



    public static double getDistanceBetweenBodies(Body b1, Body b2) {
        double dx = b1.getX() - b2.getX();
        double dy = b1.getY() - b2.getY();
        double dz = b1.getZ() - b2.getZ();
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    public static String getRandomName() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(letters[Math.abs(random.nextInt() % letters.length)]);
        }
        sb.append('-');
        for (int i = 0; i < 5; i++) {
            sb.append(numbers[Math.abs(random.nextInt() % numbers.length)]);
        }
        return sb.toString();
    }
}

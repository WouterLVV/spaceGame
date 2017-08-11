package main.world;

import main.utils.Utils;

import java.util.Random;

public class Generator {
    public static Random random = new Random();
    public static Space generateSpace(int bodyCount, double spaceSize) {
        Space s = new Space(spaceSize);
        for (int i = 0; i < bodyCount; i++) {
            s.addBody(generateRandomBody(s.getSize()));
        }
        return s;
    }

    public static Body generateRandomBody(double spaceSize) {
        return new Body(Utils.getRandomName(), random.nextDouble() * spaceSize, random.nextDouble() * spaceSize, random.nextDouble() * spaceSize, random.nextDouble() * 1000);
    }
}

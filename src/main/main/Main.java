package main.main;

import main.world.Generator;
import main.world.Ship;
import main.world.Space;

public class Main {
    public static void main(String[] args) {
        Space space = Generator.generateSpace(300, 300000);
        Ship ship = Ship.DEFAULTSHIP;
        ship.setTargetSpeed(100);
        Controller c = new Controller(space, ship);
        c.setMaxFPS(30);
        c.run();
    }
}

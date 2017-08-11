package test;

import main.main.Controller;
import main.world.Body;
import main.world.Generator;
import main.world.Ship;
import main.world.Space;
import org.junit.Test;
import org.junit.Assert;

import static main.utils.Utils.NANODIVIDINGFACTOR;

public class SpaceTests {

    @Test
    public void testSpace() {
        Space s = Generator.generateSpace(20, 1000);
        Assert.assertTrue(s.getBodies().size() == 20);
        for (Body b : s.getBodies()) {
            Assert.assertTrue(b.getX() <= 1000);
            Assert.assertTrue(b.getY() <= 1000);
            Assert.assertTrue(b.getZ() <= 1000);
        }
    }

    @Test
    public void testShip() {
        Space space = Generator.generateSpace(50, 100);
        Ship ship = new Ship("USS Test", 50, 50, 50, 11234, 247);
        space.addBody(ship);
        ship.setTargetSpeed(30);
        while (ship.speed != ship.targetSpeed) {
            ship.update(NANODIVIDINGFACTOR);
            System.out.println(ship.toString());
        }
        Assert.assertTrue(ship.speed == 30);

    }

    @Test
    public void testrotatedShip() {
        Space space = Generator.generateSpace(50, 100);
        Ship ship = new Ship("USS Test", 50, 50, 50, 11234, 247000);
        space.addBody(ship);
        ship.setTargetSpeed(30);
        ship.sigma = Math.PI/4;
        ship.theta = Math.PI/4;
        while (ship.speed != ship.targetSpeed) {
            ship.update(NANODIVIDINGFACTOR);
            System.out.println(ship.toString());
        }
        Assert.assertTrue(ship.speed == 30);

    }

    @Test
    public void testTimeImplementation() {
        Controller c = new Controller(Generator.generateSpace(2000, 10000), Ship.DEFAULTSHIP);
        c.run();

    }



}

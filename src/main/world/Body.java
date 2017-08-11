package main.world;

import main.utils.Utils;

public class Body {
    private double x,y,z;
    public final String name;
    private double mass;

    public double getMass() {
        return mass;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void move(double deltaX, double deltaY, double deltaZ) {
        x += deltaX;
        y += deltaY;
        z += deltaZ;
    }

    public Body (String _name, double _x, double _y, double _z, double _mass) {
        this.name = _name;
        this.x = _x;
        this.y = _y;
        this.z = _z;
        this.mass = _mass;
    }

    public double getDistance(Body b) {
        return Utils.getDistanceBetweenBodies(this, b);
    }

    public String toString() {
        return String.format("%s: %10.3f | %10.3f | %10.3f --- mass: %.3f", name, x, y, z, mass);
    }
}

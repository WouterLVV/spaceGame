package main.world;

import main.main.Updatable;

import static main.utils.Utils.NANODIVIDINGFACTOR;

public class Ship extends Body implements Updatable{
    public static final Ship DEFAULTSHIP = new Ship("U.S.S. Test", 0, 0, 0, 300, 20);

    public double rotX;
    public double rotY;
    public double rotZ;

    public double sigma;
    public double theta;
    public double rotation;

    public double speed;
    public double targetSpeed;

    public double engineOutput;
    public double maxAcceleration;

    public static final double TAU = Math.PI * 2;


    public Ship(String _name, double _x, double _y, double _z, double _mass, double _engineOutput) {
        super(_name, _x, _y, _z, _mass);
        this.speed = 0;
        this.targetSpeed = 0;
        this.engineOutput = _engineOutput;
        this.maxAcceleration = this.engineOutput / this.getMass();
        this.rotX = 0.0;
        this.rotY = 0.0;
        this.rotZ = 0.0;

        this.sigma = 0.0;
        this.theta = 0.0;
        this. rotation = 0.0;
    }

    public void setTargetSpeed(double _targetSpeed) {
        targetSpeed = _targetSpeed;
    }

    public void rotate(double _rotX, double _rotY, double _rotZ) {
        this.rotX = rotX + _rotX + TAU % TAU;
        this.rotY = rotY + _rotY + TAU % TAU;
        this.rotZ = rotZ + _rotZ + TAU % TAU;
    }


    public void update(long nanoSecondsPassed) {
        double timeMultiplicator = (double) nanoSecondsPassed / (double) NANODIVIDINGFACTOR;
        if (targetSpeed != speed) {
            if (targetSpeed > speed) {
                speed = Math.min(speed + maxAcceleration*timeMultiplicator, targetSpeed);
            } else if (targetSpeed < speed) {
                speed = Math.max(speed - maxAcceleration*timeMultiplicator, targetSpeed);
            }
        }

        double deltaSpeed = speed * timeMultiplicator;

        double deltaX = Math.sin(theta) * Math.sin(sigma);
        double deltaY = Math.cos(theta) * Math.sin(sigma);
        double deltaZ = Math.cos(sigma);

        deltaX *= deltaSpeed;
        deltaY *= deltaSpeed;
        deltaZ *= deltaSpeed;

        this.move(deltaX,deltaY,deltaZ);

        System.out.println(this.toString());
    }

    public String toString() {
        return super.toString() + String.format(", speed: %.3f", speed);
    }


}

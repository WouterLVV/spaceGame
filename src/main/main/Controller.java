package main.main;

import main.world.Ship;
import main.world.Space;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import static main.utils.Utils.MICRODIVIDINGFACTOR;
import static main.utils.Utils.NANODIVIDINGFACTOR;

public class Controller {
    private Space space;
    private Ship ship;

    private long timeSinceLastFrame;
    private long timeCurrentFrame;
    private long timePreviousFrame;
    private long minimumNanoTime;


    private volatile boolean stop;

    private GameLoop gameLoop;
    private UserInput userInput;

    private LinkedList<Updatable> updatables;

    public void setMinimumNanoTime(long nanoTime) {
        minimumNanoTime = nanoTime;
    }

    public void setMaxFPS(int fps) {
        setMinimumNanoTime(NANODIVIDINGFACTOR/fps);
    }

    public void addUpdatable(Updatable up) {
        updatables.add(up);
    }

    public Controller(Space _space, Ship _ship) {
        space = _space;
        ship = _ship;
        stop = false;

        updatables = new LinkedList<>();

        updatables.add(ship);

        gameLoop = new GameLoop();
        userInput = new UserInput();
        userInput.setDaemon(true);
    }

    public void run() {
        synchronized (this) {
            userInput.start();
            gameLoop.start();
        }

    }

    private class UserInput extends Thread {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        public void run() {
            try {
                br.readLine();
                stop = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private class GameLoop extends Thread {

        @Override
        public void run() {
            timePreviousFrame = System.nanoTime();
            timeCurrentFrame = System.nanoTime();
            timeSinceLastFrame = timeCurrentFrame - timePreviousFrame;

            while (!stop) {
                timePreviousFrame = timeCurrentFrame;
                timeCurrentFrame = System.nanoTime();
                timeSinceLastFrame = timeCurrentFrame - timePreviousFrame;



                if (timeSinceLastFrame < minimumNanoTime) {
                    try {
                        Thread.sleep((int)((minimumNanoTime-timeSinceLastFrame) / MICRODIVIDINGFACTOR),(int)((minimumNanoTime-timeSinceLastFrame) % (MICRODIVIDINGFACTOR)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        stop = true;
                    }
                    timeCurrentFrame = System.nanoTime();
                    timeSinceLastFrame = timeCurrentFrame - timePreviousFrame;
                }

                System.out.println("ct: " + timeCurrentFrame + " --- pt: " + timePreviousFrame + " --- dt: " + timeSinceLastFrame);


                for (Updatable u : updatables) {
                    u.update(timeSinceLastFrame);
                }
            }
        }
    }

}

package main.world;

import java.util.HashSet;
import java.util.Set;

public class Space {
    private Set<Body> bodies;
    private double size;

    public Space(double _size) {
        bodies = new HashSet<>();
        this.size = _size;
    }

    public boolean addBody(Body b) {
        return bodies.add(b);
    }

    public Set<Body> getBodies() {
        return bodies;
    }

    public double getSize() {
        return size;
    }
}

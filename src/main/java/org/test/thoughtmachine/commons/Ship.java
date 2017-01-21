package org.test.thoughtmachine.commons;

import static org.test.thoughtmachine.commons.Rotation.leftOf;
import static org.test.thoughtmachine.commons.Rotation.rightOf;

/**
 * Created by leszek on 21/01/17.
 */
public class Ship {
    private int x;
    private int y;
    private Rotation rotation;
    private boolean sunken;

    public Ship(int x, int y, Rotation rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public boolean isSunken() {
        return sunken;
    }

    public void setSunken(boolean sunken) {
        this.sunken = sunken;
    }

    public void turnLeft() {
        rotation = leftOf.get(rotation);
    }

    public void turnRight() {
        rotation = rightOf.get(rotation);
    }

    public void move() {
        switch (rotation) {
            case N : y = y + 1;
                break;
            case S : y = y - 1;
                break;
            case W : x = x - 1;
                break;
            case E : x = x + 1;
                break;
        }
    }
}

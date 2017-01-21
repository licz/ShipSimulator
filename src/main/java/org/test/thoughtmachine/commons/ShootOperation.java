package org.test.thoughtmachine.commons;

/**
 * Created by leszek on 21/01/17.
 */
public class ShootOperation extends Operation {
    private int x;
    private int y;

    public ShootOperation(int x, int y) {
        this.x = x;
        this.y = y;
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
}

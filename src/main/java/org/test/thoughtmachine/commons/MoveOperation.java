package org.test.thoughtmachine.commons;

import java.util.List;

/**
 * Created by leszek on 21/01/17.
 */
public class MoveOperation extends Operation {

    private int x;
    private int y;
    List<Movement> movements;

    public MoveOperation(int x, int y) {
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

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }
}

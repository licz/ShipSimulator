package org.test.thoughtmachine.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leszek on 21/01/17.
 */
public class Square {
    private Ship ship;
    private List<Ship> sunkenShips = new ArrayList<>();

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public List<Ship> getSunkenShips() {
        return sunkenShips;
    }

    public void setSunkenShips(List<Ship> sunkenShips) {
        this.sunkenShips = sunkenShips;
    }

    public void shoot() {
        if (ship != null) {
            ship.setSunken(true);
            sunkenShips.add(ship);
            ship = null;
        }
    }
}

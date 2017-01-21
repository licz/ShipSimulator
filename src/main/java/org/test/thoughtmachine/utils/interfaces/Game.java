package org.test.thoughtmachine.utils.interfaces;

import org.test.thoughtmachine.commons.*;

import java.util.List;

/**
 * Created by leszek on 21/01/17.
 */
public interface Game {
    void initBoard(int size);
    void placeShips(List<Ship> ships);
    void performOperation(Operation operation);
    List<Ship> getShips();
}

package org.test.thoughtmachine.utils.implementations;

import com.google.common.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.test.thoughtmachine.commons.*;
import org.test.thoughtmachine.exceptions.IllegalShipMovementException;
import org.test.thoughtmachine.exceptions.IncorrectShipPlacementException;
import org.test.thoughtmachine.exceptions.ShipSimulatorException;
import org.test.thoughtmachine.utils.interfaces.Game;

import java.util.List;

/**
 * Created by leszek on 21/01/17.
 */
public class GameImpl implements Game {

    private static final Logger logger = LoggerFactory.getLogger(GameImpl.class);

    private Square [][] board;
    private List<Ship> ships;

    @Override
    public void initBoard(int size) {
        board = new Square [size][size];
        for (int firstIterator = 0; firstIterator < size; firstIterator++) {
            for (int secondIterator = 0; secondIterator < size; secondIterator++) {
                board[firstIterator][secondIterator] = new Square();
            }
        }
    }

    @Override
    public void placeShips(List<Ship> ships) {
        logger.info("placing ships on the board");

        validate(ships);
        for (Ship ship : ships) {
            if (board[ship.getX()][ship.getY()].getShip() != null) {
                throw new IncorrectShipPlacementException("Ship already placed at " + ship.getX() + ", " + ship.getY());
            }
            board[ship.getX()][ship.getY()].setShip(ship);
        }
    }

    private void validate(List<Ship> ships) {
        for (Ship ship : ships) {
            if (ship.getX() < 0 || ship.getY() < 0 || ship.getX() > board.length - 1 || ship.getY() > board.length - 1) {
                throw new ShipSimulatorException("Ship placed outside of bounds: Coordinate: " + ship.getX() + ", " + ship.getY());
            }
        }
        this.ships = ships;
    }

    @Override
    public void performOperation(Operation operation) {
        if (operation instanceof MoveOperation) {
            performMoveOperation((MoveOperation) operation);
        } else if (operation instanceof ShootOperation) {
            performShootOperation((ShootOperation) operation);
        } else {
            throw new ShipSimulatorException("Operation unknown");
        }
    }

    private void performMoveOperation(MoveOperation moveOperation) {
        logger.info("performing move operation");

        if (board[moveOperation.getX()][moveOperation.getY()].getShip() == null) {
            throw new ShipSimulatorException("Cannot move ship which doesn't exist. Coordinate: " + moveOperation.getX() + ", " + moveOperation.getY());
        }

        Ship ship = board[moveOperation.getX()][moveOperation.getY()].getShip();
        int initialCoordinateX = ship.getX();
        int initialCoordinateY = ship.getY();

        for (Movement movement : moveOperation.getMovements()) {
            switch (movement) {
                case L : ship.turnLeft();
                    break;
                case R : ship.turnRight();
                    break;
                case M : ship.move();
            }
        }

        //if ship changed its initial position
        if ((ship.getX() != initialCoordinateX || ship.getY() != initialCoordinateY)) {

            if (ship.getX() < 0 || ship.getY() < 0 || ship.getX() > board.length - 1 || ship.getY() > board.length - 1) {
                throw new IllegalShipMovementException("Move ended outside of bounds: Initial coordinate: " + initialCoordinateX + ", " + initialCoordinateY);
            }

            if (board[ship.getX()][ship.getY()].getShip() != null) {
                throw new IllegalShipMovementException("Move ended with overlaying two ships. Initial coordinate: " + initialCoordinateX + ", " + initialCoordinateY);
            }

            board[ship.getX()][ship.getY()].setShip(ship);
            board[initialCoordinateX][initialCoordinateY].setShip(null);
        }
    }

    private void performShootOperation(ShootOperation shootOperation) {
        logger.info("performing shoot operation");

        if (shootOperation.getX() < 0 || shootOperation.getY() < 0 || shootOperation.getX() > board.length - 1 || shootOperation.getY() > board.length - 1) {
            throw new ShipSimulatorException("Shot outside of bounds: " + shootOperation.getX() + ", " + shootOperation.getY());
        }

        board[shootOperation.getX()][shootOperation.getY()].shoot();
    }

    @Override
    public List<Ship> getShips() {
        return ships;
    }

    @VisibleForTesting
    Square[][] getBoard() {
        return board;
    }
}

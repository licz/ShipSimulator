package org.test.thoughtmachine.utils.implementations;

import org.junit.Assert;
import org.junit.Test;
import org.test.thoughtmachine.commons.*;
import org.test.thoughtmachine.exceptions.IllegalShipMovementException;
import org.test.thoughtmachine.exceptions.ShipSimulatorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by leszek on 21/01/17.
 */
public class GameImplTest {

    private GameImpl board = new GameImpl();

    @Test
    public void shouldInitBoard() {
        board.initBoard(10);

        Assert.assertEquals(10, board.getBoard().length);
        Assert.assertEquals(10, board.getBoard()[1].length);
    }

    @Test
    public void shouldPlaceShips() {
        board.initBoard(5);
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(2, 3, Rotation.N));
        ships.add(new Ship(3, 2, Rotation.W));
        ships.add(new Ship(0, 0, Rotation.E));

        board.placeShips(ships);

        Assert.assertTrue(board.getBoard()[2][3].getShip() != null);
        Assert.assertEquals(Rotation.N, board.getBoard()[2][3].getShip().getRotation());
        Assert.assertTrue(board.getBoard()[3][2].getShip() != null);
        Assert.assertEquals(Rotation.W, board.getBoard()[3][2].getShip().getRotation());
        Assert.assertTrue(board.getBoard()[0][0].getShip() != null);
        Assert.assertEquals(Rotation.E, board.getBoard()[0][0].getShip().getRotation());
    }

    @Test
    public void shouldThrowExceptionWhenShipPlacedOutsideOfBounds() {
        board.initBoard(5);
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(5, 3, Rotation.N));

        try {
            board.placeShips(ships);
            fail("Exception missing");
        } catch (ShipSimulatorException exception) {
            Assert.assertEquals("Ship placed outside of bounds: Coordinate: 5, 3", exception.getMessage());
        }
    }

    @Test
    public void shouldPerformMoveOperation() {
        board.initBoard(5);
        Ship ship = new Ship(0, 0, Rotation.E);
        board.placeShips(new ArrayList<>(Arrays.asList(ship)));

        MoveOperation moveOperation = new MoveOperation(0, 0);
        moveOperation.setMovements(new ArrayList<>(Arrays.asList(Movement.M, Movement.M, Movement.M)));

        board.performOperation(moveOperation);

        Assert.assertEquals(ship, board.getBoard()[3][0].getShip());
        Assert.assertTrue(board.getBoard()[0][0].getShip() == null);
    }

    @Test
    public void shouldPerformMoveOperationTurnLeft() {
        board.initBoard(5);
        Ship ship = new Ship(0, 0, Rotation.E);
        board.placeShips(new ArrayList<>(Arrays.asList(ship)));

        MoveOperation moveOperation = new MoveOperation(0, 0);
        moveOperation.setMovements(new ArrayList<>(Arrays.asList(Movement.L, Movement.L, Movement.L)));

        board.performOperation(moveOperation);

        Assert.assertEquals(ship, board.getBoard()[0][0].getShip());
        Assert.assertEquals(Rotation.S, board.getBoard()[0][0].getShip().getRotation());
    }

    @Test
    public void shouldPerformMoveOperationTurnRight() {
        board.initBoard(5);
        Ship ship = new Ship(0, 0, Rotation.E);
        board.placeShips(new ArrayList<>(Arrays.asList(ship)));

        MoveOperation moveOperation = new MoveOperation(0, 0);
        moveOperation.setMovements(new ArrayList<>(Arrays.asList(Movement.R, Movement.R, Movement.R)));

        board.performOperation(moveOperation);

        Assert.assertEquals(ship, board.getBoard()[0][0].getShip());
        Assert.assertEquals(Rotation.N, board.getBoard()[0][0].getShip().getRotation());
    }

    @Test
    public void shouldPerformMoveOperationAndEndUpAtTheSamePlace() {
        board.initBoard(5);
        Ship ship = new Ship(0, 0, Rotation.E);
        board.placeShips(new ArrayList<>(Arrays.asList(ship)));

        MoveOperation moveOperation = new MoveOperation(0, 0);
        moveOperation.setMovements(new ArrayList<>(Arrays.asList(Movement.M, Movement.M, Movement.R, Movement.R, Movement.M, Movement.M)));

        board.performOperation(moveOperation);

        Assert.assertEquals(ship, board.getBoard()[0][0].getShip());
        Assert.assertEquals(Rotation.W, board.getBoard()[0][0].getShip().getRotation());
    }

    @Test
    public void shouldPerformMoveOperationThroughOtherShip() {
        board.initBoard(5);
        Ship ship = new Ship(0, 0, Rotation.E);
        board.placeShips(new ArrayList<>(Arrays.asList(ship, new Ship(1, 0, Rotation.N))));

        MoveOperation moveOperation = new MoveOperation(0, 0);
        moveOperation.setMovements(new ArrayList<>(Arrays.asList(Movement.M, Movement.M)));

        board.performOperation(moveOperation);

        Assert.assertEquals(ship, board.getBoard()[2][0].getShip());
        Assert.assertEquals(Rotation.E, board.getBoard()[2][0].getShip().getRotation());
    }

    @Test
    public void shouldThrowExceptionWhenShipOverlayingOtherShip() {
        board.initBoard(5);
        Ship ship = new Ship(0, 0, Rotation.E);
        board.placeShips(new ArrayList<>(Arrays.asList(ship, new Ship(1, 0, Rotation.N))));

        MoveOperation moveOperation = new MoveOperation(0, 0);
        moveOperation.setMovements(new ArrayList<>(Arrays.asList(Movement.M)));

        try {
            board.performOperation(moveOperation);
            fail("Exception missing");
        } catch (IllegalShipMovementException exception) {
            Assert.assertEquals("Move ended with overlaying two ships. Initial coordinate: 0, 0", exception.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenMovementOutOfBounds() {
        board.initBoard(5);
        Ship ship = new Ship(0, 0, Rotation.W);
        board.placeShips(new ArrayList<>(Arrays.asList(ship)));

        MoveOperation moveOperation = new MoveOperation(0, 0);
        moveOperation.setMovements(new ArrayList<>(Arrays.asList(Movement.M)));

        try {
            board.performOperation(moveOperation);
            fail("Exception missing");
        } catch (IllegalShipMovementException exception) {
            Assert.assertEquals("Move ended outside of bounds: Initial coordinate: 0, 0", exception.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenNothingToMove() {
        board.initBoard(5);
        Ship ship = new Ship(0, 0, Rotation.W);
        board.placeShips(new ArrayList<>(Arrays.asList(ship)));

        MoveOperation moveOperation = new MoveOperation(1, 0);
        moveOperation.setMovements(new ArrayList<>(Arrays.asList(Movement.M)));

        try {
            board.performOperation(moveOperation);
            fail("Exception missing");
        } catch (ShipSimulatorException exception) {
            Assert.assertEquals("Cannot move ship which doesn't exist. Coordinate: 1, 0", exception.getMessage());
        }
    }

    @Test
    public void shouldPerformShootOperationOnEmptySquare() {
        board.initBoard(5);
        Ship ship = new Ship(0, 0, Rotation.E);
        board.placeShips(new ArrayList<>(Arrays.asList(ship)));

        ShootOperation shootOperation = new ShootOperation(1, 0);

        board.performOperation(shootOperation);

        Assert.assertEquals(ship, board.getBoard()[0][0].getShip());
        Assert.assertFalse(ship.isSunken());
    }

    @Test
    public void shouldPerformShootOperationOnShip() {
        board.initBoard(5);
        Ship ship = new Ship(0, 0, Rotation.E);
        board.placeShips(new ArrayList<>(Arrays.asList(ship)));

        ShootOperation shootOperation = new ShootOperation(0, 0);

        board.performOperation(shootOperation);

        Assert.assertTrue(board.getBoard()[0][0].getShip() == null);
        Assert.assertTrue(ship.isSunken());
        Assert.assertEquals(1, board.getBoard()[0][0].getSunkenShips().size());
        Assert.assertEquals(ship, board.getBoard()[0][0].getSunkenShips().get(0));
    }

    @Test
    public void shouldThrowExceptionWhenShotOutOfBounds() {
        board.initBoard(5);
        Ship ship = new Ship(0, 0, Rotation.E);
        board.placeShips(new ArrayList<>(Arrays.asList(ship)));

        ShootOperation shootOperation = new ShootOperation(6, 0);

        try {
            board.performOperation(shootOperation);
            fail("Exception missing");
        } catch (ShipSimulatorException exception) {
            Assert.assertEquals("Shot outside of bounds: 6, 0", exception.getMessage());
        }
    }
}
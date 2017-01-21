package org.test.thoughtmachine.utils.implementations;

import org.junit.Assert;
import org.junit.Test;
import org.test.thoughtmachine.commons.*;
import org.test.thoughtmachine.exceptions.ShipSimulatorException;

import java.util.List;

/**
 * Created by leszek on 21/01/17.
 */
public class ParserImplTest {
    private ParserImpl parser = new ParserImpl();

    @Test
    public void shouldParseShips() {
        String line = "(0, 0, N) (3, 5, W) (7, 3, E)";
        List<Ship> ships = parser.parseCoordinatesLine(line);

        Assert.assertTrue(ships.size() == 3);
        Assert.assertEquals(Rotation.N, ships.get(0).getRotation());
        Assert.assertEquals(Rotation.W, ships.get(1).getRotation());
        Assert.assertEquals(Rotation.E, ships.get(2).getRotation());
        Assert.assertEquals(7, ships.get(2).getX());
        Assert.assertEquals(3, ships.get(2).getY());
    }

    @Test
    public void shouldParseMoveOperation() {
        String line = "(5, 7) MRMLMM";
        Operation operation = parser.parseOperationLine(line);

        Assert.assertTrue(operation instanceof MoveOperation);
        MoveOperation moveOperation = (MoveOperation) operation;
        Assert.assertEquals(5, moveOperation.getX());
        Assert.assertEquals(7, moveOperation.getY());
        Assert.assertEquals(6, moveOperation.getMovements().size());
    }

    @Test
    public void shouldParseShootOperation() {
        String line = "(5, 7)";
        Operation operation = parser.parseOperationLine(line);

        Assert.assertTrue(operation instanceof ShootOperation);
        ShootOperation shootOperation = (ShootOperation) operation;
        Assert.assertEquals(5, shootOperation.getX());
        Assert.assertEquals(7, shootOperation.getY());
    }

    @Test(expected = ShipSimulatorException.class)
    public void shouldThrowExceptionWhenParsingShipsAndWrongCoordinate() {
        String line = "(5, x, W";
        parser.parseCoordinatesLine(line);
    }

    @Test(expected = ShipSimulatorException.class)
    public void shouldThrowExceptionWhenParsingShipsAndWrongRotation() {
        String line = "(5, 3, x)";
        parser.parseCoordinatesLine(line);
    }

    @Test(expected = ShipSimulatorException.class)
    public void shouldThrowExceptionWhenParsingShipsAndWrongFormat() {
        String line = "[5, 3, x]";
        parser.parseCoordinatesLine(line);
    }

    @Test(expected = ShipSimulatorException.class)
    public void shouldThrowExceptionWhenParsingOperationsAndWrongCoordinate() {
        String line = "(5, x)";
        parser.parseOperationLine(line);
    }

    @Test(expected = ShipSimulatorException.class)
    public void shouldThrowExceptionWhenParsingOperationsAndWrongMovement() {
        String line = "(5, 4) MX";
        parser.parseOperationLine(line);
    }

    @Test(expected = ShipSimulatorException.class)
    public void shouldThrowExceptionWhenParsingOperationsAndWrongFormat() {
        String line = "[5, 3]";
        parser.parseOperationLine(line);
    }
}
package org.test.thoughtmachine.utils.implementations;

import org.test.thoughtmachine.commons.*;
import org.test.thoughtmachine.exceptions.ShipSimulatorException;
import org.test.thoughtmachine.utils.interfaces.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by leszek on 21/01/17.
 */
public class ParserImpl implements Parser {

    private static final String OPEN_BRACKET ="(";
    private static final String CLOSE_BRACKET =")";
    private static final String COMMA =", ";
    private static final String SUNK =" SUNK";

    @Override
    public int parseSizeLine(String line) {
        return Integer.parseInt(line.trim());
    }

    @Override
    public List<Ship> parseCoordinatesLine(String line) {
        List<Ship> ships = new ArrayList<>();
        try {
            String [] unparsedShips = line.split("\\)");
            for (String unparsedShip : unparsedShips) {
                unparsedShip = unparsedShip.replace("(", "");
                int x = Integer.parseInt(unparsedShip.split(",")[0].trim());
                int y = Integer.parseInt(unparsedShip.split(",")[1].trim());
                Rotation rotation = Rotation.valueOf(unparsedShip.split(",")[2].trim());
                ships.add(new Ship(x, y, rotation));

            }
        } catch (IllegalArgumentException e) {
            throw new ShipSimulatorException("Could not parse second line of the input file");
        }
        return ships;
    }

    @Override
    public Operation parseOperationLine(String line) {
        Operation operation = null;
        try {
            String [] unparsedOperation = line.split("\\)");

            String coordinate = unparsedOperation[0].replace("(", "");
            int x = Integer.parseInt(coordinate.split(",")[0].trim());
            int y = Integer.parseInt(coordinate.split(",")[1].trim());

            if (unparsedOperation.length == 1) {
                operation = new ShootOperation(x, y);

            } else if (unparsedOperation.length == 2) {
                operation = new MoveOperation(x, y);

                String unparsedMovements = unparsedOperation[1].trim();
                List<Movement> movements = new ArrayList<>();
                for (int iterator = 0; iterator < unparsedMovements.length(); iterator++) {
                    movements.add(Movement.valueOf(String.valueOf(unparsedMovements.charAt(iterator))));
                }
                ((MoveOperation) operation).setMovements(movements);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new ShipSimulatorException("Could not parse operation line of the input file. Line: " + line);
        }
        return operation;
    }

    @Override
    public List<String> generateOutput(List<Ship> ships) {
        List<String> lines = new ArrayList<>();
        for (Ship ship : ships) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(OPEN_BRACKET+ship.getX()+COMMA+ship.getY()+COMMA+ship.getRotation()+CLOSE_BRACKET);
            if (ship.isSunken()) {
                stringBuilder.append(SUNK);
            }
            lines.add(stringBuilder.toString());
        }
        return lines;
    }
}

package org.test.thoughtmachine.utils.interfaces;

import org.test.thoughtmachine.commons.Operation;
import org.test.thoughtmachine.commons.Ship;
import org.test.thoughtmachine.commons.Square;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by leszek on 21/01/17.
 */
public interface Parser {
    int parseSizeLine(String line);
    List<Ship> parseCoordinatesLine(String line);
    Operation parseOperationLine(String line);
    List<String> generateOutput(List<Ship> ships);
}

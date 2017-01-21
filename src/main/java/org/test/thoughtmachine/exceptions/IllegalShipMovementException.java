package org.test.thoughtmachine.exceptions;

/**
 * Created by leszek on 21/01/17.
 */
public class IllegalShipMovementException extends ShipSimulatorException {
    public IllegalShipMovementException(String s) {
        super(s);
    }
}

package transport.base;

import exceptions.Messages;
import exceptions.WrongParameterException;

/**
 * Base class for types of transport that are moving with
 * some speed.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 25.10.2016
 */
public abstract class Vehicle implements CanPassRoute {

    protected double averageSpeed;

    public double getSpeed() {
        return this.averageSpeed;
    }

    /**
     * Sets new speed to vehicle. Throws {@code WrongParameterException}
     * if new speed parameter isn't valid.
     *
     * @param newSpeed new speed to set
     * @throws WrongParameterException if newSpeed <= 0
     */
    public void setSpeed(double newSpeed) throws WrongParameterException {
        validateSpeed(newSpeed);
        this.averageSpeed = newSpeed;
    }

    /**
     * Validates speed parameter. If it's wrong
     * throws {@code WrongParameterException}.
     *
     * @param validateSpeed speed parameter to validate
     * @throws WrongParameterException if new speed is <= 0
     */
    protected void validateSpeed(double validateSpeed) throws WrongParameterException {
        if (Double.isNaN(validateSpeed) || Double.isInfinite(validateSpeed)){
            throw new WrongParameterException(Messages.ERROR + " " + validateSpeed + " is not valid values for speed");
        }
        if (Double.compare(validateSpeed, 0) <= 0) {
            throw new WrongParameterException("Error creating new " + this.getClass().getSimpleName() + ": speed can't be <= 0");
        }
    }
}

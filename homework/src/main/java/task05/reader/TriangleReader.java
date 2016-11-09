package task05.reader;

import task05.exceptions.WrongSidesNumberException;

import java.math.BigDecimal;

/**
 * Performs triangle sides reading.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 04.11.2016
 */
public abstract class TriangleReader {
    /**
     * Gets {@code paramsNumber} arguments.
     * Waiting for full array of arguments with size of {@code paramsNumber}.
     * paramsNumber must be > 0.
     *
     * @param paramsNumber number of arguments to get
     * @return array with read arguments
     */
    public abstract BigDecimal[] readSides(int paramsNumber) throws WrongSidesNumberException;
}

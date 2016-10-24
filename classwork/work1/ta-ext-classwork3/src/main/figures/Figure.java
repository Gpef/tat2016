package main.figures;

import java.math.BigDecimal;

/**
 * Represents basic figure class.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 21.10.2016
 */
public abstract class Figure {

    /**
     * Calculate and return square of a figure.
     *
     * @return square of a figure
     */
    public abstract BigDecimal getSquare();
}

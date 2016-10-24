package main.figures;

import java.math.BigDecimal;

/**
 * Represents rectangle class with two sides.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 21.10.2016
 */
public class Rectangle extends Figure {

    private BigDecimal sideA;
    private BigDecimal sideB;

    public Rectangle(BigDecimal sideA, BigDecimal sideB) throws Exception {
        if (!isValidSide(sideA) || !isValidSide(sideB)) {
            throw new Exception("Rectangle with that sides can't be real.");
        }
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public BigDecimal getSquare() {
        return sideA.multiply(sideB);
    }

    /**
     * Checks if size is valid (it's <= 0).
     *
     * @param side side to check validness
     * @return {@code true} if side is valid,
     * {@code false} - otherwise
     */
    private boolean isValidSide(BigDecimal side) {
        return !(side.compareTo(BigDecimal.valueOf(0)) == -1 ||
                side.compareTo(BigDecimal.valueOf(0)) == 0);
    }
}

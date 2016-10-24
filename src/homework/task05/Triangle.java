package homework.task05;

import homework.task05.exceptions.TriangleNotExistsException;

/**
 * Checks triangle existing and type
 *
 * @author Oleg Baslak
 * @version 1.2
 * @since 03-10-2016
 */
class Triangle {

    /* Triangle types string constants */
    private static final String COMMON = "Common";                                   //  Ordinary triangle
    private static final String EQUILATERAL = "Equilateral (ravnostoronnij)";        //  With all sides equal each other
    private static final String ISOSCELES = "Isosceles (ravnobedrennij)";            //  With two equal sides only

    private double sideA;
    private double sideB;
    private double sideC;

    Triangle(double[] sides) throws TriangleNotExistsException {
        if (IsExists(sides)) {
            sideA = sides[0];
            sideB = sides[1];
            sideC = sides[2];
        } else {
            throw new TriangleNotExistsException();
        }
    }

    /**
     * Check if triangle with {@code sides} can exist
     *
     * @param sides sides of triangle to check
     * @return {@code true} - if triangle can exist
     * {@code false} - otherwise
     */
    private boolean IsExists(double[] sides) {
        return ((sides[0] > sides[2] - sides[1]) &&
                (sides[0] > sides[1] - sides[2]) &&
                (sides[1] > sides[0] - sides[2]));
    }

    /**
     * Checks type of triangle and returns it's type as {@code String}
     *
     * @return type of triangle
     */
    String getType() {
        if (sideA == sideB && sideB == sideC) {
            return EQUILATERAL;
        }
        if (sideA == sideB || sideA == sideC || sideB == sideC) {
            return ISOSCELES;
        }
        return COMMON;
    }
}

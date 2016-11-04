package task05;

import task05.exceptions.TriangleNotExistsException;
import task05.exceptions.WrongSideLengthException;

import java.math.BigDecimal;

/**
 * Checks triangle existing and type
 *
 * @author Oleg Baslak
 * @version 1.2
 * @since 03-10-2016
 */
public class Triangle {

    /* Triangle types string constants */
    public static final String COMMON = "Common";                                   //  Ordinary triangle
    public static final String EQUILATERAL = "Equilateral (ravnostoronnij)";        //  With all sides equal each other
    public static final String ISOSCELES = "Isosceles (ravnobedrennij)";            //  With two equal sides only

    private BigDecimal sideA;
    private BigDecimal sideB;
    private BigDecimal sideC;

    public Triangle(BigDecimal sideA, BigDecimal sideB, BigDecimal sideC) throws TriangleNotExistsException, WrongSideLengthException {
        validate(sideA);
        validate(sideB);
        validate(sideC);
        if (isExists(new BigDecimal[]{sideA, sideB, sideC})) {
            this.sideA = sideA;
            this.sideB = sideB;
            this.sideC = sideC;
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
    private boolean isExists(BigDecimal[] sides) {
        return ((sides[0].compareTo(sides[1].add(sides[2])) < 0) &&
                (sides[1].compareTo(sides[0].add(sides[2])) < 0) &&
                (sides[2].compareTo(sides[0].add(sides[1])) < 0));
    }

    /**
     * Checks if number is <= 0.
     *
     * @param numberToValidate number to check
     * @throws WrongSideLengthException if number is <= 0
     */
    private void validate(BigDecimal numberToValidate) throws WrongSideLengthException {
        if (numberToValidate.compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new WrongSideLengthException(numberToValidate + " isn't valid side");
        }
    }

    /**
     * Checks type of triangle and returns it's type as {@code String}
     *
     * @return type of triangle if object isn't null. If it's null
     * returns message that triangle isn't created.
     */
    public String getType() {
        if (null == sideA || null == sideB || null == sideC) {
            return "Triangle isn't created";
        }

        if (sideA.compareTo(sideB) == 0 &&
                sideB.compareTo(sideC) == 0 &&
                sideC.compareTo(sideA) == 0) {
            return EQUILATERAL;
        }

        if (sideA.compareTo(sideB) == 0 || sideA.compareTo(sideC) == 0 || sideC.compareTo(sideB) == 0) {
            return ISOSCELES;
        }

        return COMMON;
    }
}

package task05;

import task05.exceptions.TriangleException;
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
    public static final String STRING_COMMON = "Common";                                   //  Ordinary triangle
    public static final String STRING_EQUILATERAL = "Equilateral (ravnostoronnij)";        //  With all sides equal each other
    public static final String STRING_ISOSCELES = "Isosceles (ravnobedrennij)";            //  With two equal sides only

    public static final short TYPE_COMMON = 1;
    public static final short TYPE_EQUILATERAL = 2;
    public static final short TYPE_ISOSCELES = 3;


    private BigDecimal sideA;
    private BigDecimal sideB;
    private BigDecimal sideC;

    private short type;

    public Triangle(BigDecimal sideA, BigDecimal sideB, BigDecimal sideC) throws TriangleException {
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
        type = defineType(new BigDecimal[]{sideA, sideB, sideC});
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
     * Checks if number is <= 0 or null.
     *
     * @param numberToValidate number to check
     * @throws WrongSideLengthException if number is <= 0 or null
     */
    private void validate(BigDecimal numberToValidate) throws TriangleException {
        if (null == numberToValidate) {
            throw new WrongSideLengthException("Side is null");
        }

        if (numberToValidate.compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new WrongSideLengthException(numberToValidate + " isn't valid side");
        }
    }

    /**
     * Finds what type is triangle and returns
     * const value assigned to this type.
     *
     * @param sides sides of triangle to find type
     * @return value assigned to triangle's type
     */
    private short defineType(BigDecimal[] sides) {
        if (sides[0].compareTo(sides[1]) == 0 &&
                sides[1].compareTo(sides[2]) == 0 &&
                sides[2].compareTo(sides[0]) == 0) {
            return TYPE_EQUILATERAL;
        }

        if (sides[0].compareTo(sides[1]) == 0 ||
                sides[0].compareTo(sides[2]) == 0 ||
                sides[2].compareTo(sides[1]) == 0) {
            return TYPE_ISOSCELES;
        }

        return TYPE_COMMON;
    }

    /**
     * @return type of triangle as short variable
     */
    public short getType() {
        return type;
    }

    /**
     * Returns triangle's type as {@code String}
     *
     * @return type of triangle if object isn't null. If it's null
     * returns message that triangle isn't created.
     */
    public String getTypeName() {
        if (null == sideA || null == sideB || null == sideC) {
            return "Triangle isn't created";
        }
        switch (type) {
            case TYPE_EQUILATERAL: {
                return STRING_EQUILATERAL;
            }
            case TYPE_ISOSCELES: {
                return STRING_ISOSCELES;
            }
        }

        return STRING_COMMON;
    }
}

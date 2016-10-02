package com.rct.homework.lesson1.task04;

import com.rct.homework.lesson1.task04.exceptions.ComplexRootsOnlyException;
import com.rct.homework.lesson1.task04.exceptions.WrongParamsSizeException;

/**
 * Finds roots of equations
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 30-09-2016
 */
class SquareEquation {

    /* a, b, c params of ax2 + bx + c = 0 */
    private double paramA;
    private double paramB;
    private double paramC;

    /**
     * @param params array with params. Must contain {@code} Main.NUMBER_OF_ARGUMENTS
     *               arguments
     * @throws WrongParamsSizeException when params array don't has 3 params
     */
    SquareEquation(double[] params) throws WrongParamsSizeException {
        if (params.length != Main.NUMBER_OF_ARGUMENTS) {
            throw new WrongParamsSizeException();
        } else {
            paramA = params[0];
            paramB = params[1];
            paramC = params[2];
        }
    }

    /**
     * Find discriminant of equation
     *
     * @return counted discriminant
     */
    private double findDiscriminant() {
        double d;
        d = this.paramB * this.paramB - 4 * this.paramA * this.paramC;
        return d;
    }

    /**
     * Finds roots of equation
     *
     * @return array with counted root
     * @throws ComplexRootsOnlyException if equation has complex only roots
     */
    double[] findRoots() throws ComplexRootsOnlyException {
        double discriminant = this.findDiscriminant();
        if (discriminant < 0) {
            throw new ComplexRootsOnlyException();
        }

        double[] roots = new double[2];
        if (Validator.isZero(discriminant)) {
            roots[0] = roots[1] = (-1 * this.paramB) / 2 * paramA;
        } else if (discriminant > 0) {
            roots[0] = (-1 * this.paramB + Math.sqrt(discriminant)) / 2 * paramA;
            roots[1] = (-1 * this.paramB - Math.sqrt(discriminant)) / 2 * paramA;
        }

        return roots;
    }
}

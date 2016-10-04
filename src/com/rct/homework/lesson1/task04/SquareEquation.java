package com.rct.homework.lesson1.task04;

import com.rct.homework.lesson1.task04.exceptions.ComplexRootsOnlyException;
import com.rct.homework.lesson1.task04.exceptions.NoRootsException;
import com.rct.homework.lesson1.task04.exceptions.NotQuadraticEquationException;
import com.rct.homework.lesson1.task04.exceptions.WrongParamsSizeException;

/**
 * Finds roots of equations
 *
 * @author Oleg Baslak
 * @version 1.2
 * @since 30-09-2016
 */
class SquareEquation {

    /* a, b, c params of ax2 + bx + c = 0 */
    private double paramA;
    private double paramB;
    private double paramC;

    /**
     * @param params array with params. Must contain {@code Main.NUMBER_OF_ARGUMENTS}
     *               arguments
     * @throws WrongParamsSizeException when params array don't has 3 params
     */
    SquareEquation(double[] params) throws WrongParamsSizeException, NotQuadraticEquationException {
        if (params.length != Main.NUMBER_OF_ARGUMENTS) {
            throw new WrongParamsSizeException();
        }
        if (Validator.isZero(params[0])) {
            throw new NotQuadraticEquationException();
        }
        paramA = params[0];
        paramB = params[1];
        paramC = params[2];
    }

    /**
     * Find discriminant of equation
     *
     * @return counted discriminant
     */
    private double findDiscriminant() {
        return this.paramB * this.paramB - 4 * this.paramA * this.paramC;
    }

    /**
     * Finds roots of equation
     *
     * @return array with counted root
     * @throws ComplexRootsOnlyException if equation has complex only roots
     * @throws NoRootsException          if only {@code c} param presented, so there no methods
     *                                   to find roots
     * @see Validator
     */
    double[] findRoots() throws ComplexRootsOnlyException, NoRootsException {
        double[] roots = new double[2];

        // eq. c = 0
        if (Validator.isZero(paramA) && Validator.isZero(paramB)) {
            throw new NoRootsException();
        }

        // eq. normal ax2 + bx + c = 0
        double discriminant = this.findDiscriminant();
        if (discriminant < 0) {
            throw new ComplexRootsOnlyException();
        }
        if (Validator.isZero(discriminant)) {
            roots[0] = roots[1] = (-1 * this.paramB) / 2 * paramA;
        } else if (discriminant > 0) {
            roots[0] = (-1 * this.paramB + Math.sqrt(discriminant)) / 2 * paramA;
            roots[1] = (-1 * this.paramB - Math.sqrt(discriminant)) / 2 * paramA;
        }
        return roots;
    }
}

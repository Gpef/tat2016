package task05;

/**
 * Validating arguments get from user
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 03-10-2016
 */
class Validator {

    /**
     * Checks if {@code double} number on input == 0
     *
     * @param number number to check
     * @return {@code true} if {@code number} is zero
     * {@code false} otherwise
     */
    static boolean isZero(double number) {
        return Double.isInfinite(1. / number);
    }

}

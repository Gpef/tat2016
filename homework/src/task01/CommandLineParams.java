package task01;

/**
 * Entrance point in program. Function <code>main</code>
 * prints arguments in reversed order
 *
 * @author Oleg Baslak
 * @version 1.1
 * @since 30-09-2016
 */
class CommandLineParams {

    /**
     * Prints <code>args</code> array in reversed order
     *
     * @param args arguments array to revert
     */
    public static void main(String[] args) {
        for (int i = args.length - 1; i >= 0; i--) {
            System.out.println("Argument " + i + " = " + args[i]);
        }
    }
}

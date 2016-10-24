package homework.task02;

/**
 * Prints "Hello, %username% to console
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 30-09-2016
 */
class HelloUser {

    /**
     * Prints "Hello, %username%, where username taken from
     * first argument in <code>args</code>. If it's null,
     * "Hello, %username will be printed%"
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String username = "%username%";
        if (args.length != 0) {
            username = args[0];
        }
        System.out.println("Hello, " + username + "!");
    }
}

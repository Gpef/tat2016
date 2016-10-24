package homework.task07;

import homework.task07.localisation.MessageStrings;

import java.util.Scanner;

/**
 * Getting input from console
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public abstract class InputController {

    /**
     * Gets string from console input.
     * Skips empty string.
     *
     * @return <code>string</code> from console
     */
    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        String output = null;
        while (true) {
            output = scanner.nextLine();
            if (!output.equals("")) {
                break;
            }
            else {
                System.out.println(MessageStrings.TRY_AGAIN_MESSAGE);
            }
        }
        return output;
    }
}

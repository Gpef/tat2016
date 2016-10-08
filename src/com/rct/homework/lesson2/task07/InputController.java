package com.rct.homework.lesson2.task07;

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
        }
        return output;
    }
}

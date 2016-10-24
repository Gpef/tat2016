package main;

import java.awt.*;

/**
 * Provides useful methods to work with {@code java.awt.Color}
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 21.10.2016
 */
public abstract class ColorUtils {

    /**
     * Makes color darker in a value of specified step
     *
     * @param color      color to make darker
     * @param darkerStep size of making darker
     * @return darker color then {@code Color} color
     */
    public static Color getDarkerColor(Color color, int darkerStep) {
        return new Color(color.getRed() - darkerStep,
                color.getGreen() - darkerStep,
                color.getBlue() - darkerStep);
    }

    /**
     * Converts {@code Color} object to string
     * in hexadecimal view.
     *
     * @param color color object to parse
     * @return parsed hex value of {@code Color} color
     */
    public static String getHexString(Color color) {
        return String.format("#%02x%02x%02x",
                color.getRed(),
                color.getGreen(),
                color.getBlue());
    }
}

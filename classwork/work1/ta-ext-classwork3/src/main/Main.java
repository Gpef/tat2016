package main;

import main.figures.Rectangle;

/**
 *
 */
public class Main {

    /**
     * Entrance point to the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            Rectangle rectangle = new ConsoleInput().getSquare();
            System.out.println("rectangle's square: " + rectangle.getSquare());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

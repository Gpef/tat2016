package main;

import main.export.HTMLExporter;

import java.io.File;
import java.io.IOException;

/**
 * Main class of application.
 */
public class Main {

    private static String outputFilepath = "output.html";

    /**
     * Entrance point to the program. Generates list of number from
     * 0 to random number and exports it into html file in
     * table. Cell colors of this table changes from white to black color
     * in gradient.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            HTMLExporter exporter = new HTMLExporter();
            exporter.export(new DataGenerator().generate(), new File(outputFilepath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

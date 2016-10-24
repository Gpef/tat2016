package main;

import java.io.File;

/**
 * Main class of application. Gets folder path from args and
 * searches all folders and files in that directory.
 * Also prints full paths to all found files.
 */
public class Main {

    private static String folderToSearch = "D:\\Install\\Games";

    /**
     * Entrance point to the application
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            FileSearcher fileSearcher = new FileSearcher();
            fileSearcher.searchFiles(new File(folderToSearch), args[0]);
            fileSearcher.showFiles();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

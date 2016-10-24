package homework.taks09;

import java.io.File;

/**
 * Main class of application. Searches all folders and files in opened directory
 * and writes information such as file name, creation data and size into html file.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.10.2016
 */
public class Main {

    private static final String outputFilename = "output.html";


    /**
     * Entrance point to the application
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            String userDirectory = System.getProperty("user.dir");
            FileSearcher fileSearcher = new FileSearcher();
            fileSearcher.searchFiles(new File(userDirectory));

            Importer importer = new HtmlImporter();
            importer.getImport(fileSearcher.getFoldersInDir(),
                    fileSearcher.getFilesInDir(),
                    new File(outputFilename));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

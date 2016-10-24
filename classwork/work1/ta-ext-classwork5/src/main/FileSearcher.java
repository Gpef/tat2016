package main;

import java.io.File;
import java.util.ArrayList;

/**
 * Performs files search in folder and it's subfolders and
 * showing this files into console.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 21.10.2016
 */
public class FileSearcher {

    private ArrayList<File> filesInDir;

    public FileSearcher() {
        filesInDir = new ArrayList<>();
    }

    /**
     * Finds all files in directory and subdirectories with specified extension
     *
     * @param directory folder to search
     * @throws Exception if directory isn't a folder
     */
    public void searchFiles(File directory, String extension) throws Exception {
        if (!directory.isDirectory()) {
            throw new Exception("directory argument isn't a folder");
        }

        File[] folderEntries = directory.listFiles();
        for (File entry : folderEntries) {
            if (entry.isFile() && getFileExtension(entry).equals(extension)) {
                filesInDir.add(entry);
            }
            if (entry.isDirectory()) {
                searchFiles(entry, extension);
            }
        }
    }

    /**
     * @param file file to get extension
     * @return extension of file
     */
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    /**
     * Prints all found files into console
     */
    public void showFiles() {
        for (File file : filesInDir) {
            System.out.println(file.getAbsolutePath());
        }
    }
}

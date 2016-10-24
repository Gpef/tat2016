package homework.taks09;

import java.io.File;
import java.util.ArrayList;

/**
 * Performs files and folders search. Found files can be
 * later got with the using of get methods.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.10.2016
 */
public class FileSearcher {

    private ArrayList<File> filesInDir;
    private ArrayList<File> foldersInDir;

    public FileSearcher() {
        filesInDir = new ArrayList<>();
        foldersInDir = new ArrayList<>();
    }

    /**
     * @return list of files found in folder
     */
    public ArrayList<File> getFilesInDir() {
        return filesInDir;
    }

    /**
     * @return list of folders found in parent folder
     */
    public ArrayList<File> getFoldersInDir() {
        return foldersInDir;
    }

    /**
     * Search all files in set directory. They can be
     * received later with the using of getters methods.
     *
     * @param directory folder where we need to find files.
     * @throws Exception if directory isn't a folder
     */
    public void searchFiles(File directory) throws Exception {
        if (!directory.isDirectory()) {
            throw new Exception("directory argument isn't a folder");
        }
        File[] folderEntries = directory.listFiles();
        for (File entry : folderEntries) {
            if (entry.isFile()) {
                filesInDir.add(entry);
            }
            if (entry.isDirectory()) {
                foldersInDir.add(entry);
            }
        }
    }

    /**
     * Returns size of the folder by recursive search of
     * all files in subfolders in parent folder.
     * Can work very slow, if there big amount of little
     * files in many folders (like temp caches).
     *
     * @param folder folder to find size
     * @return size of the folder
     */
    public static long getFolderSize(File folder) {
        long size = 0;
        File[] folderEntries = folder.listFiles();
        if (folderEntries != null) {
            for (File entry : folderEntries) {
                if (entry.isFile()) {
                    size += entry.length();
                }
                if (entry.isDirectory()) {
                    size += getFolderSize(entry);
                }
            }
        }
        return size;
    }

    /**
     * Cuts size in bytes to size in Kilobytes. Rounds to entire value.
     * If size is lower than 1024 byte, returns 1 Kb.
     *
     * @param bytesToConvert bytes size to convert into Kb
     * @return converted size if it is > 1024, 1 if size is < 0
     * and 0 if size equals 0
     */
    public static long bytesToKib(long bytesToConvert) {
        if (bytesToConvert == 0) {
            return 0;
        }
        if (bytesToConvert < 1024) {
            return 1;
        }
        return bytesToConvert / 1024;
    }
}

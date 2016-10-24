package taks09;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.10.2016
 */
public abstract class Importer {

    /**
     * Writes data about folders and files in directory into output file
     *
     * @param folders    list of folders oto import
     * @param files      list of files ot import
     * @param outputFile file into all data will be written
     * @throws IOException if errors with file existing or reading\writing rights occurred
     */
    public abstract void getImport(ArrayList<File> folders, ArrayList<File> files, File outputFile) throws IOException;
}

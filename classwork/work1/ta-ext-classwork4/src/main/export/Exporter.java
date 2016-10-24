package main.export;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 21.10.2016
 */
public abstract class Exporter {

    /**
     * Exports data to file
     *
     * @param outputFile  file into all data will be written
     * @throws IOException if errors with file existing or reading\writing rights occurred
     */
    public abstract void export(ArrayList<Integer> exportData, File outputFile) throws IOException;
}


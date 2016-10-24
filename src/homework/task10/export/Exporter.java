package homework.task10.export;

import homework.task10.server.Server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Providing methods for export of server's data (names and pings) to file.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 17.10.2016
 */
public abstract class Exporter {

    /**
     * Exports {@code Server} addresses and pings to file
     *
     * @param serversData servers to export
     * @param outputFile file into all data will be written
     * @throws IOException if errors with file existing or reading\writing rights occurred
     */
    public abstract void export(ArrayList<Server> serversData, File outputFile) throws IOException;
}

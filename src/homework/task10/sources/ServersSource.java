package homework.task10.sources;

import homework.task10.server.Server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Represents base class for entities that search server addresses in
 * various sources.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 19.10.2016
 */
public abstract class ServersSource {

    /**
     * Performs addresses search in source. Adds all valid addresses
     * to {@code ArrayList} and finally returns this list.
     *
     * @return {@code ArrayList} of addresses if there is at least 1 address in source,
     * @throws InvalidParameterException if source doesn't contain any server addresses
     * @throws FileNotFoundException     if {@code File} source isn't exists
     */
    public abstract ArrayList<Server> getServers() throws IOException;
}

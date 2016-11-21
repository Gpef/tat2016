package io.reader;

import java.util.HashMap;

/**
 * Performs reading commands from different sources.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public interface CommandReader {

    /**
     * Checks, if there is any unread command properties left.
     *
     * @return true - if there is one more command in reader source,
     * false = otherwise
     */
    public boolean hasNext();

    /**
     * Read next command properties in source. If there is no - return null.
     *
     * @return next read command, or null if there are no commands
     * left in source
     */
    public HashMap<String, String> next();
}

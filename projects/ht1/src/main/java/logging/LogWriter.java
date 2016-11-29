package logging;

import java.io.IOException;

/**
 * Loggers should implement. Provides interface to logging command.
 * Classes that implement interface realises their own log system (console, file, etc.).
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public interface LogWriter {

    /**
     * Writes {@code String} logMessage to log file.
     *
     * @param logMessage message to write
     */
    public void log(String logMessage) throws IOException;
}

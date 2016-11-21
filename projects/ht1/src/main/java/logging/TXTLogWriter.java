package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Performs writing logs of commands execution. By default, writes logs into
 * ./logs/tests-log.txt
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public final class TXTLogWriter implements LogWriter {

    private static final String DEFAULT_LOG_FILE = "logs\\tests-log.txt";
    private File logFile;

    public TXTLogWriter() throws IOException {
        this(new File(DEFAULT_LOG_FILE));
    }

    public TXTLogWriter(File logFile) throws IOException {
        if (logFile != null) {
            this.logFile = logFile;
        } else {
            throw new IOException("Can't set null as file for TXTLogWriter. Try non-null value");
        }
    }

    @Override
    public void log(String logMessage) throws IOException {
        createLogFolder();
        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(logMessage);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new IOException("Can't log into file. " + e.getMessage());
        }
    }

    /**
     * Sets new file for logging if the file isn't null.
     *
     * @param file new file for logs
     * @throws IOException if file is null
     */
    public void setLogFile(File file) throws IOException {
        if (null != file) {
            logFile = file;
        } else {
            throw new IOException("Can't set null as file for TXTLogWriter. Try non-null value");
        }
    }

    /**
     * Checks existing of logs folder and creates it if necessary.
     */
    private void createLogFolder() throws IOException {
        File logsDir = logFile.getParentFile();
        if (null == logsDir) {
            throw new IOException("Can't create folder for logs: " + logFile.getAbsolutePath());
        }

        if (!logsDir.exists()) {
            logsDir.mkdirs();
        }
    }
}

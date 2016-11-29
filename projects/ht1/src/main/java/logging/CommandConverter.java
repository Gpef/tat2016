package logging;

import java.io.IOException;

/**
 * Provides useful methods to work with commands logging.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 16.11.2016
 */
public final class CommandConverter {

    /**
     * Formats command name, arguments, execution time and result
     * to string for logging.
     * <p>
     * Formatted string is like
     * "${testResult} [${commandArgs[0]} "${commandArgs[1]}" .."${commandArgs[size]}"] ${executionTime}"
     * Example: "
     * + [open "https://www.google.com" "3000"] 1.699"
     *
     * @param testResult    if test was done successful, "+" will be printed,
     *                      otherwise - "!" will be printed
     * @param commandName   command name to print
     * @param commandArgs   command arguments (parameters)
     * @param executionTime command execution time
     * @return formatted for logging command execution information
     */
    public String toLog(
            boolean testResult, String commandName, String[] commandArgs, long executionTime) throws
            IOException {
        String result = testResult ? "+" : "!";
        StringBuilder command = new StringBuilder();
        command.append(commandName);
        for (String arg : commandArgs) {
            command.append(" \"" + arg + "\"");
        }
        return result + " [" + command + "] " + executionTime / 1000d;
    }
}

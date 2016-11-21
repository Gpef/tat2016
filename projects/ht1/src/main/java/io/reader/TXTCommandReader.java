package io.reader;

import exceptions.CommandReaderException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Performs reading commands from TXT file. They must fit next template:
 * ${command_name} "${arg1}" "{arg2}" ..
 * <p>
 * Each command start with new line, args must be in '"' and spaced.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public class TXTCommandReader implements CommandReader {
    private List<String> fileLines;
    private List<HashMap<String, String>> commands;
    private int count = 0;
    private Parser parser = new Parser();

    public TXTCommandReader(String filePath) throws CommandReaderException {
        try {
            if (null == filePath) {
                throw new Exception("File path for TXTCommandReader is null");
            }
            fileLines = Files.readAllLines(Paths.get(filePath));
            commands = new Parser().parse(fileLines);
        } catch (Exception e) {
            throw new CommandReaderException("Error reading file: " + e.getMessage());
        }
    }


    /**
     * Checks, if there is any unread commands left.
     *
     * @return true - if there is one more command in txt,
     * false - otherwise
     */
    @Override
    public boolean hasNext() {
        return commands.size() > count;
    }

    /**
     * Read next command in txt.
     *
     * @return next read command. If next command doesn't exist
     * returns null.
     */
    @Override
    public HashMap<String, String> next() {
        if (hasNext()) {
            return commands.get(count++);
        }

        return null;
    }

    /**
     * Performs parsing txt to dedicated property maps
     * (one map for one command).
     */
    public final class Parser {
        private final static String COMMAND_PREFIX = "--command";
        private final static String PREFIX = "--";

        /**
         * Parses line to command names and arguments to
         * list of properties maps.
         *
         * @param lines lines list to parse
         * @return parsed list of maps with command names and args
         */
        public List<HashMap<String, String>> parse(List<String> lines) throws Exception {
            List<HashMap<String, String>> parsed = new ArrayList<>();
            HashMap<String, String> nextCommand;
            for (int i = 0; i < lines.size(); i++) {
                nextCommand = new HashMap<>();

                String nextLine = lines.get(i).replaceAll("[\uFEFF-\uFFFF]", "");
                if ((nextLine).startsWith(COMMAND_PREFIX)) {
                    String[] properties = nextLine.split(PREFIX);
                    for (int j = 1; j < properties.length; j++) {
                        int delimeterIndex = properties[j].indexOf("=");
                        if (delimeterIndex == -1) {
                            break;
                        }
                        nextCommand.put(properties[j].substring(0, delimeterIndex).trim(),
                                properties[j].substring(delimeterIndex + 1).trim());
                    }
                }
                if (nextCommand.size() > 0) {
                    parsed.add(nextCommand);
                }
            }

            return parsed;
        }
    }
}

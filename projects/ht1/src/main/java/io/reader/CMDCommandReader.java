package io.reader;

import exceptions.CommandReaderException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Performs reading commands from command line. They must fit next template:
 * --command ${COMMAND_NAME} --${PROPERTY_NAME_1} "${PROPERTY_VALUE_1}"
 * --${PROPERTY_NAME_2} "{PROPERTY_VALUE_2}" --command ${NEXT_COMMAND_NAME} ..
 * <p>
 * Each command start with --command, args must be in '"'.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public class CMDCommandReader implements CommandReader {
    private int count = 0;
    private List<HashMap<String, String>> commands;

    public CMDCommandReader(String[] args) throws CommandReaderException {
        if (null == args || args.length < 1) {
            throw new CommandReaderException("Can't create CMDCommandReader because arguments array is empty or null." +
                    " -> Can't create CMDCommandReader");
        }

        commands = new Parser().parse(args);
    }

    @Override
    public boolean hasNext() {
        return commands.size() > count;
    }

    /**
     * Read next command in list of commands.
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
     * Performs parsing args array to dedicated property maps
     * (one map for one command).
     */
    public class Parser {
        private final static String COMMAND_PREFIX = "--command";
        private final static String PREFIX = "--";

        /**
         * Parses whole args array into commands property list with
         * dedicated property maps.
         *
         * @param args arguments array to parse
         * @return parsed list of maps with command names and args
         */
        public List<HashMap<String, String>> parse(String[] args) {
            List<HashMap<String, String>> parsed = new ArrayList<>();
            HashMap<String, String> nextCommand;
            for (int i = 0; i < args.length; i++) {
                if (args[i].startsWith(COMMAND_PREFIX)) {
                    nextCommand = new HashMap<>();
                    nextCommand.put(args[i].substring(PREFIX.length(), args[i].length()), args[i + 1]);
                    i += 2;
                    while (args.length >= i + 1 && !args[i].startsWith(COMMAND_PREFIX)) {
                        if (args[i].startsWith(PREFIX)) {
                            nextCommand.put(args[i].substring(PREFIX.length(), args[i].length()), args[i + 1]);
                        }
                        i++;
                    }

                    parsed.add(nextCommand);
                    i--;
                }
            }

            return parsed;
        }
    }

}

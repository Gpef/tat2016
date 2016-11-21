package io.reader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Performs reading commands from JSON file. They must fit next template:
 * {
 * "commands": [
 * {
 * "name": "${COMMAND1_NAME}",
 * "${ARG1_NAME}": "${ARG1}",
 * "${ARG2_NAME}": "${ARG2}",
 * },
 * {
 * "name": "${COMMAND2_NAME}",
 * "${ARG1_NAME}": "${ARG1}",
 * "${ARG2_NAME}": "${ARG2}",
 * }
 * ]
 * }
 * <p>
 * Each command must be in root "commands" json array.
 * Everything out of "commands" will be ignored.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public class JSONCommandReader implements CommandReader {
    List<HashMap<String, String>> commands;
    int count = 0;

    public JSONCommandReader(File jsonFile) throws IOException {
        if (null == jsonFile) {
            throw new IOException("File path for XMLCommandReader is null");
        }
        commands = new Parser().parse(jsonFile);
    }

    /**
     * Checks, if there is any unread commands left.
     *
     * @return true - if there is one more command in json,
     * false - otherwise
     */
    @Override
    public boolean hasNext() {
        return commands.size() > count;
    }

    /**
     * Read next command in json.
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
     * Performs parsing json to dedicated property maps
     * (one map for one command).
     */
    public class Parser {
        private static final String COMMANDS_ARRAY_NAME = "commands";

        /**
         * Parses Json array "commands" and it's data with command name and it's arguments
         * to list of property maps.
         *
         * @param file json file to parse
         * @return parsed list of maps with command names and args
         */
        public List<HashMap<String, String>> parse(File file) throws FileNotFoundException {
            JsonParser parser = new JsonParser();
            JsonObject mainObject = parser.parse(new FileReader(file)).getAsJsonObject();
            JsonArray commandsArray = mainObject.getAsJsonArray(COMMANDS_ARRAY_NAME);

            ArrayList<HashMap<String, String>> commands = new ArrayList<>();
            for (JsonElement nextCommandInJson : commandsArray) {
                JsonObject nextCommand = nextCommandInJson.getAsJsonObject();

                HashMap<String, String> commandProperties = new HashMap<>();
                for (LinkedTreeMap.Entry entry : nextCommand.entrySet()) {
                    commandProperties.put((String) entry.getKey(), cleanUp(entry.getValue().toString()));
                }
                commands.add(commandProperties);
            }

            return commands;
        }

        /**
         * Deletes first and last '"' symbols from string
         *
         * @param toClean string to delete '"'
         * @return cleaned from first and last '"' symbols
         */
        private String cleanUp(String toClean) {
            return toClean.substring(1, toClean.length() - 1);
        }
    }

}

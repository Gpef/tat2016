package command;

import exceptions.CommandException;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Represents command working with web pages that will be executed
 * with specified {@code String[]} arguments.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public abstract class Command {
    public static final String PROPERTY_COMMAND_NAME = "command";
    public static final String PROPERTY_HREF = "href";
    public static final String PROPERTY_LINK_NAME = "link_name";
    public static final String PROPERTY_TEXT = "text";
    public static final String PROPERTY_TITLE = "title";
    public static final String PROPERTY_URL = "url";
    public static final String PROPERTY_TIMEOUT = "timeout";


    /**
     * Checks if command with specified arguments set can be built and builds it.
     */
    public abstract static class Builder {

        /**
         * Checks if builder can build command with specified
         * args.
         *
         * @param args arguments which specify command, contains command name at args[0] and
         *             command arguments at the next indexes
         * @return {@code true} if the builder can build command with
         * specified options, {@code false} - otherwise
         */
        public abstract boolean canBuild(HashMap<String, String> args);

        /**
         * Builds command based on specific argument list.
         *
         * @param args arguments which specify command, contains command name with key and
         *             command arguments at the next indexes
         * @return built command
         */
        public abstract Command build(HashMap<String, String> args) throws CommandException;
    }

    /**
     * Executes command depending on command implementation.
     *
     * @param driver webdriver of browser to work with
     */
    public abstract boolean execute(WebDriver driver);

    /**
     * @return name of command
     */
    public abstract String getName();

    /**
     * Search command property with specified name. Returns
     * found property or null if there is no property with selected
     * name.
     *
     * @param propertyName property to search
     * @return property value if exists, null - otherwise.
     */
    public abstract String getProperty(String propertyName);

    /**
     * @return array of this command arguments
     */
    public abstract String[] getPropertiesValues();

    protected String[] getPropertiesValues(HashMap<String, String> properties, String command) {
        ArrayList<String> propertiesValues = new ArrayList<>();
        Iterator<?> iterator = properties.values().iterator();
        while (iterator.hasNext()){
            propertiesValues.add((String) iterator.next());
        }

        propertiesValues.remove(command);
        return propertiesValues.toArray(new String[propertiesValues.size()]);
    }
}

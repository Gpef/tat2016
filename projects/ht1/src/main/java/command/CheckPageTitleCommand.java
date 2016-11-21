package command;

import exceptions.CommandException;
import exceptions.InvalidParamsException;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * Checks if in currently opened url in webdriver
 * specified page title is presented.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public class CheckPageTitleCommand extends Command {
    private static final String COMMAND_NAME = "checkPageTitle";
    private final HashMap<String, String> properties;

    private CheckPageTitleCommand(HashMap<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public boolean execute(WebDriver driver) {
        return driver.getTitle().equals(properties.get(PROPERTY_TITLE));
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }

    @Override
    public String getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    @Override
    public String[] getPropertiesValues() {
        return getPropertiesValues(properties, COMMAND_NAME);
    }

    /**
     * Checks if CheckPageTitleCommand with specified arguments
     * can be built and builds it.
     */
    public static class Builder extends Command.Builder {

        @Override
        public boolean canBuild(HashMap<String, String> properties) {
            return properties.get(PROPERTY_COMMAND_NAME) != null &&
                    properties.get(PROPERTY_COMMAND_NAME).equals(COMMAND_NAME) &&
                    properties.get(PROPERTY_TITLE) != null;
        }

        @Override
        public Command build(HashMap<String, String> properties) throws CommandException {
            if (canBuild(properties)) {
                return new CheckPageTitleCommand(properties);
            } else {
                throw new InvalidParamsException("Can't build " + CheckPageTitleCommand.class.getSimpleName() +
                        "with " + String.valueOf(properties.entrySet()));
            }
        }
    }
}

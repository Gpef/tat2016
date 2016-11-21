package command;

import exceptions.CommandException;
import exceptions.InvalidParamsException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * Checks if in currently opened url in webdriver
 * link with specified href is presented.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public class CheckLinkPresentByHrefCommand extends Command {
    private static final String COMMAND_NAME = "checkLinkPresentByHref";
    private final HashMap<String, String> properties;

    private CheckLinkPresentByHrefCommand(HashMap<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public boolean execute(WebDriver driver) {
        return driver.findElements(By.xpath("//a[@href=\"" + properties.get(PROPERTY_HREF) + "\"]")).size() > 0;
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
     * Checks if CheckLinkPresentByHrefCommand with specified arguments
     * can be built and builds it.
     */
    public static class Builder extends Command.Builder {

        @Override
        public boolean canBuild(HashMap<String, String> properties) {
            return properties.get(PROPERTY_COMMAND_NAME) != null &&
                    properties.get(PROPERTY_COMMAND_NAME).equals(COMMAND_NAME) &&
                    properties.get(PROPERTY_HREF) != null;
        }

        @Override
        public Command build(HashMap<String, String> properties) throws CommandException {
            if (canBuild(properties)) {
                return new CheckLinkPresentByHrefCommand(properties);
            } else {
                throw new InvalidParamsException("Can't build " + CheckLinkPresentByHrefCommand.class.getSimpleName() +
                        "with " + String.valueOf(properties.entrySet()));
            }
        }
    }

}

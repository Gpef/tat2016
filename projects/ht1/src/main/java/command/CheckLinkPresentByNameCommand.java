package command;

import exceptions.CommandException;
import exceptions.InvalidParamsException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * Checks if in currently opened url in webdriver
 * link with specified text is presented.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public class CheckLinkPresentByNameCommand extends Command {
    private static final String COMMAND_NAME = "checkLinkPresentByName";
    private final HashMap<String, String> properties;

    private CheckLinkPresentByNameCommand(HashMap<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public boolean execute(WebDriver driver) {
        return driver.findElements(By.xpath("//a[text()=\"" + properties.get(PROPERTY_LINK_NAME) + "\"]")).size() > 0;
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
     * Checks if CheckLinkPresentByNameCommand with specified arguments
     * can be built and builds it.
     */
    public static class Builder extends Command.Builder {

        @Override
        public boolean canBuild(HashMap<String, String> properties) {
            if (null == properties){
                return false;
            }
            return properties.get(PROPERTY_COMMAND_NAME) != null &&
                    properties.get(PROPERTY_COMMAND_NAME).equals(COMMAND_NAME) &&
                    properties.get(PROPERTY_LINK_NAME) != null;
        }

        @Override
        public Command build(HashMap<String, String> properties) throws CommandException {
            if (null == properties) {
                throw new InvalidParamsException("Can't build command with null properties");
            }
            if (canBuild(properties)) {
                return new CheckLinkPresentByNameCommand(properties);
            } else {
                throw new InvalidParamsException("Can't build " + CheckLinkPresentByNameCommand.class.getSimpleName() +
                        "with " + String.valueOf(properties.entrySet()));
            }
        }
    }
}

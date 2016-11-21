package command;

import exceptions.CommandException;
import exceptions.InvalidParamsException;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Opens specified url in webdriver.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public class OpenCommand extends Command {
    private static final String COMMAND_NAME = "open";
    private final HashMap<String, String> properties;

    private OpenCommand(HashMap<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public boolean execute(WebDriver driver) {
        try {
            driver.manage().timeouts().pageLoadTimeout(Long.parseLong(properties.get(PROPERTY_TIMEOUT)), TimeUnit.MILLISECONDS);
            driver.get(properties.get(PROPERTY_URL));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
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
     * Checks if OpenCommand with specified arguments
     * can be built and builds it.
     */
    public static class Builder extends Command.Builder {

        @Override
        public boolean canBuild(HashMap<String, String> properties) {
            if (null == properties){
                return false;
            }
            try {
                Long.parseLong(properties.get(PROPERTY_TIMEOUT));
            } catch (Exception e) {
                return false;
            }

            return properties.get(PROPERTY_COMMAND_NAME) != null &&
                    properties.get(PROPERTY_COMMAND_NAME).equals(COMMAND_NAME) &&
                    properties.get(PROPERTY_URL) != null;
        }

        @Override
        public Command build(HashMap<String, String> properties) throws CommandException {
            if (null == properties) {
                throw new InvalidParamsException("Can't build command with null properties");
            }
            if (canBuild(properties)) {
                return new OpenCommand(properties);
            } else {
                throw new InvalidParamsException("Can't build " + OpenCommand.class.getSimpleName() +
                        "with " + String.valueOf(properties.entrySet()));
            }
        }
    }
}

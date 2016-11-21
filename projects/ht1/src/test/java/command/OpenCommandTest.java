package command;

import exceptions.CommandException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static org.testng.Assert.*;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 21.11.2016
 */
public class OpenCommandTest {
    private static final String TEST_URL = "http://test.url";
    private static final String TEST_TIMEOUT = "3000";
    private static final String PROPERTY_COMMAND = "command";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_TIMEOUT = "timeout";
    private static final String COMMAND_NAME = "open";
    private Command command;
    private HashMap<String, String> properties;
    private String[] propertiesStrings;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() throws Exception {
        properties = new HashMap<>();
        properties.put(PROPERTY_COMMAND, COMMAND_NAME);
        properties.put(PROPERTY_URL, TEST_URL);
        properties.put(PROPERTY_TIMEOUT, TEST_TIMEOUT);
        propertiesStrings = new String[]{COMMAND_NAME, TEST_URL, TEST_TIMEOUT};
        command = new OpenCommand.Builder().build(properties);
    }

    @Test
    public void testGetProperty() throws Exception {
        softAssert.assertEquals(command.getProperty(PROPERTY_COMMAND), COMMAND_NAME);
        softAssert.assertEquals(command.getProperty(PROPERTY_URL), TEST_URL);
        softAssert.assertEquals(command.getProperty(PROPERTY_TIMEOUT), TEST_TIMEOUT);
    }

    @Test
    public void testGetNotExistProperty() throws Exception {
        assertNull(command.getProperty("not exist property"));
    }

    @Test
    public void testGetPropertiesValues() throws Exception {
        softAssert.assertEqualsNoOrder(command.getPropertiesValues(), propertiesStrings);
        softAssert.assertEqualsNoOrder(command.getPropertiesValues(properties, COMMAND_NAME), propertiesStrings);
    }

    @Test(dataProvider = "truePropertiesOpen", dataProviderClass = CommandBuildDataProviders.class)
    public void testBuilderCanBuildTrue(HashMap<String, String> properties) throws Exception {
        assertTrue(new OpenCommand.Builder().canBuild(properties));
    }

    @Test(dataProvider = "falsePropertiesOpen", dataProviderClass = CommandBuildDataProviders.class)
    public void testBuilderCanBuildFalse(HashMap<String, String> properties) throws Exception {
        assertFalse(new OpenCommand.Builder().canBuild(properties));
    }

    @Test(dataProvider = "truePropertiesOpen", dataProviderClass = CommandBuildDataProviders.class)
    public void testBuilderBuild(HashMap<String, String> properties) throws Exception {
        new OpenCommand.Builder().build(properties);
    }

    @Test(dataProvider = "falsePropertiesOpen", dataProviderClass = CommandBuildDataProviders.class,
            expectedExceptions = CommandException.class)
    public void testBuilderNotBuild(HashMap<String, String> properties) throws Exception {
        new OpenCommand.Builder().build(properties);
    }
}
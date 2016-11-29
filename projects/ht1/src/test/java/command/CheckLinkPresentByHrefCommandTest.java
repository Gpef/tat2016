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
public class CheckLinkPresentByHrefCommandTest {
    private static final String TEST_HREF = "http://test.href";
    private static final String PROPERTY_COMMAND = "command";
    private static final String PROPERTY_HREF = "href";
    private static final String COMMAND_NAME = "checkLinkPresentByHref";
    private Command command;
    private HashMap<String, String> properties;
    private String[] propertiesStrings;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod

    public void setUp() throws Exception {
        properties = new HashMap<>();
        properties.put(PROPERTY_COMMAND, COMMAND_NAME);
        properties.put(PROPERTY_HREF, TEST_HREF);
        propertiesStrings = new String[]{COMMAND_NAME, TEST_HREF};
        command = new CheckLinkPresentByHrefCommand.Builder().build(properties);
    }

    @Test
    public void testGetProperty() throws Exception {
        softAssert.assertEquals(command.getProperty(PROPERTY_COMMAND), COMMAND_NAME);
        softAssert.assertEquals(command.getProperty(PROPERTY_HREF), PROPERTY_HREF);
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

    @Test(dataProvider = "truePropertiesCheckLinkPresentByHref", dataProviderClass = CommandBuildDataProviders.class)
    public void testBuilderCanBuildTrue(HashMap<String, String> properties) throws Exception {
        assertTrue(new CheckLinkPresentByHrefCommand.Builder().canBuild(properties));
    }

    @Test(dataProvider = "falsePropertiesCheckLinkPresentByHref", dataProviderClass = CommandBuildDataProviders.class)
    public void testBuilderCanBuildFalse(HashMap<String, String> properties) throws Exception {
        assertFalse(new CheckLinkPresentByHrefCommand.Builder().canBuild(properties));
    }

    @Test(dataProvider = "truePropertiesCheckLinkPresentByHref", dataProviderClass = CommandBuildDataProviders.class)
    public void testBuilderBuild(HashMap<String, String> properties) throws Exception {
        new CheckLinkPresentByHrefCommand.Builder().build(properties);
    }

    @Test(dataProvider = "falsePropertiesCheckLinkPresentByHref", dataProviderClass = CommandBuildDataProviders.class,
            expectedExceptions = CommandException.class)
    public void testBuilderNotBuild(HashMap<String, String> properties) throws Exception {
        new CheckLinkPresentByHrefCommand.Builder().build(properties);
    }
}
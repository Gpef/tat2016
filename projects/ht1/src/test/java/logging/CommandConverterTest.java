package logging;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 20.11.2016
 */
public class CommandConverterTest {

    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
    }

    @Test
    public void testToLog() throws Exception {
        CommandConverter converter = new CommandConverter();
        String[] args = new String[]{"open", "http://www.google.com", "3000"};
        softAssert.assertEquals("+ [open \"http://www.google.com\"  \"3000\" ] 0.288",
                converter.toLog(true, "open", args, 288L));

        args =  new String[]{"checkLinkPresentByHref", "https://mail.google.com/mail/?tab=wm"};
        softAssert.assertEquals("! [checkLinkPresentByHref \"https://mail.google.com/mail/?tab=wm\" ] 0.017",
                converter.toLog(false, "checkLinkPresentByHref", args, 17L));
    }

}
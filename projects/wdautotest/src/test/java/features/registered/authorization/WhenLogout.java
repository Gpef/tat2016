package features.registered.authorization;

import features.AbstractFeature;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.EndUserSteps;

import java.util.concurrent.TimeUnit;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.12.2016
 */
@RunWith(SerenityRunner.class)
public class WhenLogout extends AbstractFeature {

    @Managed
    public WebDriver driver;

    @Steps
    public EndUserSteps user;

    @Before
    public void setUp() throws Exception {
        driver.manage().timeouts().implicitlyWait(8000L, TimeUnit.MILLISECONDS);
        user.atLogout().loginFirst();
    }

    @Test
    @Title("After logout authorization page opens")
    public void userShouldSeeLoginPageAfterLogout() throws Exception {
        user.atLogout().logout();
        user.atLogout().shouldSeeLogoutMessage();
    }
}

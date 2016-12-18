package features.registered.authorization;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import steps.EndUserSteps;

import java.util.concurrent.TimeUnit;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 05.12.2016
 */
//@RunWith(SerenityRunner.class)
public class WhenFailedLogin {

    @Managed
    public WebDriver driver;

    @Steps
    public EndUserSteps user;

    @Before
    public void setUp() throws Exception {
        driver.manage().timeouts().implicitlyWait(8000L, TimeUnit.MILLISECONDS);
    }

    @Test
    @Title("User can't login with unregistered data and will see error")
    public void userCantLoginWithoutRegisteredProfile() throws Exception {
        user.atLogin().openLoginPage();
        user.atLogin().loginWithLoginPassword("not exists", "not exists");
        user.atLogin().shouldSeeLoginErrorMessage();
    }
}

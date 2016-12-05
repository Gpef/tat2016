package autotests.features.registered.authorization;

import autotests.features.AbstractFeature;
import autotests.steps.EndUserSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.12.2016
 */
@RunWith(SerenityParameterizedRunner.class)
public class WhenLogin extends AbstractFeature {

    @Managed
    public WebDriver driver;

    @Steps
    public EndUserSteps user;

    @TestData
    public static Collection<Object[]> loginData() {
        return Arrays.asList(
                new Object[][]{
                {"admin", "admin"},
                {"editor", "editor"},
                {"subscriber", "subscriber"},
                {"author","author"},
                {"contributor", "contributor"}
        });
    }

    private final String login;
    private final String password;

    public WhenLogin(String login, String password) {
        super();
        this.login = login;
        this.password = password;
    }

    @Before
    public void setUp() throws Exception {
        driver.manage().timeouts().implicitlyWait(8000L, TimeUnit.MILLISECONDS);
    }

    @Test
    @Title("Registered user with type can login")
    public void userShouldSeeMainPageAfterLogin() throws Exception {
        user.atLogin().openLoginPage();
        user.atLogin().loginWithLoginPassword(login, password);
        user.atLogin().shouldSeeProfileInfo();
        user.atLogin().shouldSeeLogoutLink();
    }
}



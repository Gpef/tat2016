package features.registered.authorization;

import features.AbstractFeature;
import steps.EndUserSteps;
import utils.database.DatabaseWorker;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.TestData;
import org.junit.After;
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

    private DatabaseWorker dbWorker = new DatabaseWorker();

    @TestData
    public static Collection<Object[]> loginData() {
        return Arrays.asList(
                new Object[][]{
                        {"editor", "editor", DatabaseWorker.EDITOR},
                });
    }

    private final String login;
    private final String password;
    private final DatabaseWorker.Role role;

    public WhenLogin(String login, String password, DatabaseWorker.Role role) {
        super();
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Before
    public void setUp() throws Exception {
        dbWorker.addUser(login, password, role);
        driver.manage().timeouts().implicitlyWait(8000L, TimeUnit.MILLISECONDS);
    }

    @Test
    @Title("Registered users (all roles) with login, password can login")
    public void userShouldSeeMainPageAfterLogin() throws Exception {
        user.atLogin().openLoginPage();
        user.atLogin().loginWithLoginPassword(login, password);

        user.atLogin().shouldSeeDashboardMenu();
        user.atLogin().shouldSeeLogoutLink();
        user.atLogin().shouldSeeWelcomeMessage();
        user.atLogin().shouldSeeProfileAvatar();
    }

    @After
    public void tearDown() throws Exception {
        dbWorker.deleteUser(login);
    }
}



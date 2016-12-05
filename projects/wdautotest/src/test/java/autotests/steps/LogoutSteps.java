package autotests.steps;

import autotests.ui.LoginPage;
import autotests.ui.ProfilePopup;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 04.12.2016
 */
public class LogoutSteps extends ScenarioSteps {
    private LoginPage onLoginPage;
    private ProfilePopup onProfilePopup;

    @Step("Need to be logged in to log out")
    public void loginFirst() {
        onLoginPage.open();
        onLoginPage.login("subscriber", "subscriber");
    }

    @Step("Logging out after clickng 'Log Out'")
    public void logout() {
        onProfilePopup.logout();
        assertTrue("Error message wasn't found", onLoginPage.loginMessage.isPresent());
        assertTrue(
                "Error message doesn't equals to specified",
                onLoginPage.loginMessage.getText().equals("You are now " + "logged out.1")
        );
    }

    @Step("After log out authorization page opens with message")
    public void shouldSeeLogoutMessage(){
        assertTrue("Message says user is logged out wasn't found", onLoginPage.loginMessage.isPresent());
    }
}

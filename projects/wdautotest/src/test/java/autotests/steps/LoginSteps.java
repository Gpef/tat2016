package autotests.steps;

import autotests.ui.AdministrationPage;
import autotests.ui.LoginPage;
import autotests.ui.ProfilePopup;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.12.2016
 */
public class LoginSteps extends ScenarioSteps {
    private AdministrationPage onAdministrationPage;
    private ProfilePopup onProfilePopup;
    private LoginPage onLoginPage;

    @Step("Open login page")
    public void openLoginPage() {
        onLoginPage.open();
    }

    @Step("Login with login:{0}, pass:{1}")
    public void loginWithLoginPassword(String login, String password) {
        onLoginPage.login(login, password);
    }

    @Step("Dashboard menu should be present")
    public void shouldSeeDashBoardMenu(){
        assertTrue("Dashboard menu wasn't found", onAdministrationPage.dashboardMenuMain.isPresent());
    }

    @Step("Profile info in the top bar should appear")
    public void shouldSeeProfileInfo() {
        assertTrue("Profile name wasn't found", onProfilePopup.profileName.isPresent());
    }

    @Step("Logout link in the top bar should appear")
    public void shouldSeeLogoutLink() {
        assertTrue("Log Out link in profile popup wasn't found", onProfilePopup.logoutLink.isPresent());
    }

    @Step("Login error message should appear")
    public void shouldSeeLoginErrorMessage() {
        assertTrue("Login error message wasn't found", onLoginPage.loginMessage.isPresent());
    }
}

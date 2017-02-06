package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import ui.elements.AdminBarElement;
import ui.elements.ProfilePopupElement;
import ui.pages.LoginPage;
import ui.pages.WPAdminPage;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.12.2016
 */
public class LoginSteps extends ScenarioSteps {
    private LoginPage onLoginPage;
    private WPAdminPage onWPAdminPage;
    private ProfilePopupElement onProfilePopupElement;
    private AdminBarElement bar;

    @Step("Open login page")
    public void openLoginPage() {
        onLoginPage.open();
    }

    @Step("Login with login:{0}, pass:{1}")
    public void loginWithLoginPassword(String login, String password) {
        onLoginPage.login(login, password);
    }

    @Step("Dashboard menu should be present")
    public void shouldSeeDashboardMenu() {
        assertTrue("Dashboard menu isn't visible or wasn't found", onWPAdminPage.adminBar.isVisible());
    }

    @Step("User avatar should present in the top bar")
    public void shouldSeeProfileAvatar() {
        assertTrue("Avatar isn't visible", onProfilePopupElement.isAvatarVisible());
    }

    @Step("Logout link in the top bar should appear")
    public void shouldSeeLogoutLink() {
        assertTrue("Log Out link in profile popup wasn't found", onProfilePopupElement.isLogoutLinkPresent());
    }

    @Step("Welcome message with name 'Howdy, username' should appear")
    public void shouldSeeWelcomeMessage() {
        assertTrue("Welcome message with 'Howdy' wasn't found",
                onProfilePopupElement.isHowdyVisible()
        );
    }

    @Step("Message about login error should appear")
    public void shouldSeeLoginErrorMessage(){
        assertTrue("", onLoginPage.isErrorMessageVisible());
    }
}

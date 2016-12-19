package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import ui.elements.ProfilePopupElement;
import ui.pages.LoginPage;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 04.12.2016
 */
public class LogoutSteps extends ScenarioSteps {
    private LoginPage onLoginPage;
    private ProfilePopupElement onProfilePopupElement;

    @Step("Need to be logged in to log out")
    public void loginFirst() {
        onLoginPage.open();
        onLoginPage.login("admin", "admin");
    }

    @Step("Focus on profile popup to see  Log Out link")
    public void focusOnProfilePopup(){
        onProfilePopupElement.focus();
    }

    @Step("Logging out after clicking 'Log Out'")
    public void logout() {
        onProfilePopupElement.clickLogout();
        assertTrue("Error message wasn't found", onLoginPage.isErrorMessageVisible());
        assertTrue("Error message doesn't equals to specified",
                onLoginPage.getMessageText().equals("You are now " + "logged out.")
        );
    }

    @Step("After log out authorization page opens with message")
    public void shouldSeeLogoutMessage() {
        assertTrue("Message says user is logged out wasn't found", onLoginPage.isErrorMessageVisible());
    }
}

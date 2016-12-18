package ui.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import ui.elements.LoginFormElement;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 16.12.2016
 */
@DefaultUrl("http://localhost:8888/wp-login.php")
public class LoginPage extends PageObject {
    public LoginFormElement loginForm;

    public static final String LOGIN_FIELD = "//input[@id='user_login']";
    public static final String PASSWORD_FIELD = "//input[@id='user_pass']";
    public static final String SUBMIT_BUTTON = "//input[@type='submit']";
    public static final String ERROR_MESSAGE = "//p[@class='message']|//div[@id='login_error']";

    public void login(String login, String password) {
        loginForm.typeLoginData(login, password);
        loginForm.pressSubmitButton();
        /*$(LOGIN_FIELD).sendKeys(login);
        $(PASSWORD_FIELD).sendKeys(password);
        $(SUBMIT_BUTTON).click();*/
    }

    public boolean isErrorMessageVisible() {
        return $(ERROR_MESSAGE).isVisible();
    }

    public String getMessageText() {
        return $(ERROR_MESSAGE).getText();
    }
}

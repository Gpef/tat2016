package ui.elements;

import net.serenitybdd.core.pages.PageObject;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 16.12.2016
 */
public class LoginFormElement extends PageObject {
    public static final String LOGIN_FIELD = "//input[@id='user_login']";
    public static final String PASSWORD_FIELD = "//input[@id='user_pass']";
    public static final String SUBMIT_BUTTON = "//input[@type='submit']";
    public static final String ERROR_MESSAGE = "//p[@class='message']|//div[@id='login_error']";

    public void typeLoginData(String login, String password) {
        $(LOGIN_FIELD).sendKeys(login);
        $(PASSWORD_FIELD).sendKeys(password);
    }

    public void pressSubmitButton() {
        $(SUBMIT_BUTTON).click();
    }

    public boolean isErrorMessageVisible() {
        return $(LOGIN_FIELD).isVisible();
    }

    public String getMessageText() {
        return $(ERROR_MESSAGE).getText();
    }
}

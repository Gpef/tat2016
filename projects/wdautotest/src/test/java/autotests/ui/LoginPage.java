package autotests.ui;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.12.2016
 */
@DefaultUrl("http://localhost:8888/wp-login.php")
public class LoginPage extends PageObject {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='user_login']")
    public WebElementFacade loginField;

    @FindBy(xpath = "//input[@id='user_pass']")
    public WebElementFacade passwordField;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElementFacade submitButton;

    @FindBy(xpath = "//p[@class='message']|//div[@id='login_error']")
    public WebElementFacade loginMessage;

    public void login(String login, String password) {
        loginField.type(login);
        passwordField.type(password);
        submitButton.click();
    }
}

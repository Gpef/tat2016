package autotests.ui;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 04.12.2016
 */
public class ProfilePopup extends PageObject {

    @FindBy(xpath = "//span[@class='display-name']")
    public WebElementFacade profileName;

    @FindBy(xpath = "//a[text()='Log Out']")
    public WebElementFacade logoutLink;

    public void logout() {
        logoutLink.click();
    }
}

package ui.elements;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.interactions.Actions;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 04.12.2016
 */
public class ProfilePopupElement extends PageObject {
    public static final String POPUP = "//li[@id='wp-admin-bar-my-account']//img[@class='avatar avatar-26 photo']";

    public static final String NAME_HOWDY = "//a[@class='ab-item']";
    public static final String AVATAR = "//img[@class='avatar avatar-26 photo']";
    public static final String NAME = "//span[@class='display-name']";
    public static final String LOGOUT_LINK = "//a[text()='Log Out']";

    /**
     * Click logout button to log out of profie
     *
     * @throws InvalidElementStateException if click link isn't visible, disabled or
     *                                      isn't present.
     */
    public void clickLogout() throws InvalidElementStateException {
        $(LOGOUT_LINK).click();
    }

    /**
     * Focus on popup to see it's content
     */
    public void focus() {
        new Actions(getDriver()).moveToElement($(POPUP)).perform();
        getDriver().switchTo().activeElement();
    }

    public boolean isAvatarVisible() {
        return $(AVATAR).isVisible();
    }

    public boolean isHowdyVisible() {
        return $(NAME_HOWDY).isVisible();
    }

    public boolean isLogoutLinkPresent() {
        return $(LOGOUT_LINK).isPresent();
    }
}

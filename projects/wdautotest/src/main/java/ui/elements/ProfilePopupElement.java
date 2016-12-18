package ui.elements;

import net.serenitybdd.core.pages.PageObject;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 04.12.2016
 */
public class ProfilePopupElement extends PageObject {
    public static final String NAME_HOWDY = "//a[@class='ab-item']";
    public static final String AVATAR= "//img[@class='avatar avatar-26 photo']";
    public static final String NAME = "//span[@class='display-name']";
    public static final String LOGOUT_LINK = "//a[text()='Log Out']";

    public void logout() {
        $(LOGOUT_LINK).click();
    }

    public boolean isAvatarVisible(){
        return $(AVATAR).isVisible();
    }

    public boolean isHowdyVisible(){
        return $(NAME_HOWDY).isVisible();
    }

    public boolean isLogoutLinkPresent(){
        return $(LOGOUT_LINK).isPresent();
    }
}

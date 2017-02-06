package ui.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.12.2016
 */
@DefaultUrl("http://localhost:8888")
public class MainPage extends PageObject {
    public static final String LOGIN_LINK = "//a[text()='Log in']";
    public static final String LAST_POST_LINK = "//main[@class='site-main']//a[@rel='bookmark']";

    public void openLastPost() {
        $(LAST_POST_LINK).click();
    }
}

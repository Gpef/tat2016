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
@DefaultUrl("http://localhost:8888")
public class MainPage extends PageObject {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='Log in']")
    public WebElementFacade loginLink;

    @FindBy(xpath = "//main[@class='site-main']//a[@rel='bookmark']")
    public WebElementFacade lastPostLink;

    public void openLastPost() {
        lastPostLink.click();
    }
}

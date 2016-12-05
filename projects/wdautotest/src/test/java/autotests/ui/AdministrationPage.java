package autotests.ui;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.12.2016
 */
@DefaultUrl("http://localhost:8888/wp-admin/profile.php")
@At(urls = "#HOST/wp-admin/profile.php")
public class AdministrationPage extends PageObject {

    public AdministrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='wp-menu-name']")
    public WebElementFacade dashboardMenuMain;
}

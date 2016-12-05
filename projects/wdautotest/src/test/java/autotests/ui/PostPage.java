package autotests.ui;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 04.12.2016
 */
public class PostPage extends PageObject {

    @FindBy(xpath = "//span[@class='author vcard']")
    public WebElementFacade authorVCard;

    @FindBy(xpath = "//a[@class='url fn n']")
    public WebElementFacade authorNickname;

    @FindBy(xpath = "//h2[@id='reply-title']")
    public WebElementFacade replyHeaderSection;

    @FindBy(xpath = "//textarea[@id='comment']")
    public WebElementFacade replyTextArea;

    @FindBy(xpath = "//input[@id='submit']")
    public WebElementFacade replyPostSubmit;

    public void postReply(String reply){
        replyTextArea.sendKeys(reply);
        replyPostSubmit.click();
    }

    public String getAuthorName() {
        return authorNickname.getText();
    }
}

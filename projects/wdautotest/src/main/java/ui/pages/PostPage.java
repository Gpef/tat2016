package ui.pages;

import net.serenitybdd.core.pages.PageObject;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 04.12.2016
 */
public class PostPage extends PageObject {
    public static final String VCARD = "//span[@class='author vcard']";
    public static final String NICKNAME = "//a[@class='url fn n']";
    public static final String POST_TEXT = "//div[@class='entry-content']";


    public static final String REPLY_HEADER = "//h2[@id='reply-title']";
    public static final String REPLY_TEXT_AREA = "//textarea[@id='comment']";
    public static final String REPLY_POST_SUBMIT = "//input[@id='submit']";

    public void postReply(String reply) {
        $(REPLY_TEXT_AREA).sendKeys(reply);
        $(REPLY_POST_SUBMIT).click();
    }

    public boolean isAuthorVCardPresent() {
        return $(VCARD).isPresent();
    }

    public boolean isPostTextPresent(){
        return $(POST_TEXT).isPresent();
    }

    public String getAuthorName() {
        return $(NICKNAME).getText();
    }
}

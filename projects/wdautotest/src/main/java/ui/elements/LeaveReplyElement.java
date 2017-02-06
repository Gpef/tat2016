package ui.elements;

import net.serenitybdd.core.pages.PageObject;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 18.12.2016
 */
public class LeaveReplyElement extends PageObject {
    public static final String RESPOND_BLOCK = "//div[@class='comment-respond']";
    public static final String COMMENT_TEXTAREA = "//textarea[@id='comment']";
    public static final String INPUT_AUTHOR = "//input[@id='author']";
    public static final String INPUT_EMAIL = "//input[@id='email']";
    public static final String INPUT_SUBMIT = "//input[@id='submit']";
}

package ui.elements;

import net.serenitybdd.core.pages.PageObject;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 18.12.2016
 */
public class CommentsElement extends PageObject {
    public static final String COMMENTS_LIST = "//ol[@class='comment-list']";

    public boolean isPresent() {
        return $(COMMENTS_LIST).isPresent();
    }

}

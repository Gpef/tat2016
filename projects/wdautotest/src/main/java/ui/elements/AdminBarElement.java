package ui.elements;

import net.serenitybdd.core.pages.PageObject;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 16.12.2016
 */
public class AdminBarElement extends PageObject {
    public static final String ADMIN_BAR = "//div[@id='wpadminbar']";

    public boolean isVisible(){
        return $(ADMIN_BAR).isVisible();
    }
}

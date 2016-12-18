package ui.pages;

import ui.elements.AdminBarElement;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.12.2016
 */
@DefaultUrl("http://localhost:8888/wp-admin/profile.php")
public class WPAdminPage extends PageObject {
    public AdminBarElement adminBar;
}

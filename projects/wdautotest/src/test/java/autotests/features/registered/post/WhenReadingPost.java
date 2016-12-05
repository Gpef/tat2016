package autotests.features.registered.post;

import autotests.features.AbstractFeature;
import autotests.steps.EndUserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 04.12.2016
 */
@RunWith(SerenityRunner.class)
public class WhenReadingPost extends AbstractFeature {

    @Managed
    public WebDriver driver;

    @Steps
    public EndUserSteps user;

    @Before
    public void setUp() throws Exception {
        driver.manage().timeouts().implicitlyWait(10000L, TimeUnit.MILLISECONDS);
    }

    @Test
    @Title("Post page should contain author info, post text, comments and reply form")
    public void userShouldSeePostInfoAndCommentsAtPostPage() throws Exception{
        user.atReadingPost().openMainPage();
        user.atReadingPost().openLastPost();
        user.atReadingPost().shouldSeeAuthorVCard();
        user.atReadingPost().shouldSeeLeaveReply();
    }
}

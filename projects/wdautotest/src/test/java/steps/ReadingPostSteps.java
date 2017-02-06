package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import ui.pages.MainPage;
import ui.pages.PostPage;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.12.2016
 */
public class ReadingPostSteps extends ScenarioSteps {
    private MainPage onMainPage;
    private PostPage onPostPage;

    @Step("Open main page with posts")
    public void openMainPage() {
        onMainPage.open();
    }

    @Step("Open last post page")
    public void openLastPost() {
        onMainPage.openLastPost();
    }

    @Step("Post's author information should be present in vcard")
    public void shouldSeeAuthorVCard() {
        assertTrue("Author vcard wasn't found", onPostPage.isAuthorVCardPresent());
    }

    @Step("Reply section should be present")
    public void shouldSeePostText() {
        assertTrue("Post text wasn't found", onPostPage.isPostTextPresent());
    }
}

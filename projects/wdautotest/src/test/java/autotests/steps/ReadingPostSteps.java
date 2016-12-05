package autotests.steps;

import autotests.ui.MainPage;
import autotests.ui.PostPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.assertj.core.api.JUnitSoftAssertions;

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
        assertTrue("Author vcard wasn't found", onPostPage.authorVCard.isPresent());
    }

    @Step("Reply section should be present")
    public void shouldSeeLeaveReply() {
        JUnitSoftAssertions softAssertions = new JUnitSoftAssertions();
        softAssertions.assertThat(onPostPage.replyHeaderSection.isPresent()).isTrue();
        softAssertions.assertThat(onPostPage.replyTextArea.isPresent()).isTrue();
    }
}

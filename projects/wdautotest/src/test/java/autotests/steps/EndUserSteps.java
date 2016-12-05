package autotests.steps;

import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.steps.StepFactory;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.12.2016
 */
public class EndUserSteps extends ScenarioSteps {

    public EndUserSteps(Pages pages) {
        super(pages);
    }

    public LoginSteps atLogin() {
        return new StepFactory(getPages()).getStepLibraryFor(LoginSteps.class);
    }

    public ReadingPostSteps atReadingPost() {
        return new StepFactory(getPages()).getStepLibraryFor(ReadingPostSteps.class);
    }

    public LogoutSteps atLogout() {
        return new StepFactory(getPages()).getStepLibraryFor(LogoutSteps.class);
    }

    public LeaveReplySteps atLeavingReply() {
        return new StepFactory(getPages()).getStepLibraryFor(LeaveReplySteps.class);
    }
}

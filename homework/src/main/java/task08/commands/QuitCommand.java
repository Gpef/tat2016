package task08.commands;

import task08.commands.options.CommandOptionsProvider;
import task08.storage.Storage;

/**
 * Represents command used to quit application.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 11.10.2016
 */
public class QuitCommand extends Command {
    private static final String NAME = "quit";
    private static final int PARAMS = 0;

    /**
     * {@code QuitCommand} builder performs options validation
     * and creates a new {@code QuitCommand} command.
     */
    public static class Builder extends Command.Builder {

        @Override
        public boolean canBuild(CommandOptionsProvider options) {
            return !(!options.getCommand().equals(NAME) || options.getNumberOfOptions() != PARAMS);
        }

        @Override
        public Command build(CommandOptionsProvider options) {
            return new QuitCommand();
        }
    }

    @Override
    public void execute(Storage storage) {
        System.out.print("Bye!");
        System.exit(0);
    }
}

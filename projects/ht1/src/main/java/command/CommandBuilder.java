package command;

import exceptions.CommandException;
import exceptions.InvalidParamsException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Entity that keeps list of Commands Builders. It requests builders
 * to build command with specified arguments and returns built command
 * if args match one of commands.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public class CommandBuilder {
    private ArrayList<Command.Builder> builders = new ArrayList<>();

    /**
     * Adds command builder to the builders chain.
     *
     * @param builder builder to add to the chain
     * @return CommandBuilder object back to continue adding Builders
     * to the chain
     */
    public CommandBuilder add(Command.Builder builder) {
        builders.add(builder);
        return this;
    }

    /**
     * Performing build of {@code Command} command.
     *
     * @return built command that corresponds inputted options
     * @throws InvalidParamsException if unsupported command was send
     */
    public Command build(HashMap<String, String> properties) throws CommandException {
        if (null == properties) {
            throw new InvalidParamsException("String[] args for command building is null");
        }

        for (Command.Builder builder : builders) {
            if (null == builder) {
                throw new CommandException("Command builder " + builder + " is null");
            }
            if (builder.canBuild(properties)) {
                return builder.build(properties);
            }
        }

        throw new InvalidParamsException("Unsupported command \"" + properties.values().toString() + "\"");
    }

    /**
     * Adds all supported commands builders to builders chain.
     */
    public CommandBuilder initDefaultBuilders() {
        builders.add(new OpenCommand.Builder());
        builders.add(new CheckLinkPresentByNameCommand.Builder());
        builders.add(new CheckLinkPresentByHrefCommand.Builder());
        builders.add(new CheckPageTitleCommand.Builder());
        builders.add(new CheckPageContainsCommand.Builder());
        return this;
    }
}

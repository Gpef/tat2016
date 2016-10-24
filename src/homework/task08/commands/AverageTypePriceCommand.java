package homework.task08.commands;

import homework.task08.commands.options.CommandOptionsProvider;
import homework.task08.exceptions.StorageException;
import homework.task08.storage.Accountant;
import homework.task08.storage.Storage;

/**
 * Represents command that show average price of
 * specified product type at the storage.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 11.10.2016
 */
public class AverageTypePriceCommand extends Command {
    private static final String NAME = "average-price";
    private static final int PARAMS = 1;
    private String type = "";

    public AverageTypePriceCommand(String _type) {
        type = _type;
    }

    /**
     * {@code AverageTypePrice} builder performs options validation
     * and creates a new {@code AverageTypePriceCommand} command.
     */
    public static class Builder extends Command.Builder {

        @Override
        public boolean canBuild(CommandOptionsProvider options) {
            return !(!options.getCommand().equals(NAME) || options.getNumberOfOptions() != PARAMS);

        }

        @Override
        public Command build(CommandOptionsProvider options) {
            return new AverageTypePriceCommand(options.getOptionalParams().get(0));
        }
    }

    @Override
    public void execute(Storage storage) {
        try {
            System.out.println("Average price of " + type + ": " +
                    new Accountant().findAveragePriceOfType(storage, type));
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
    }
}

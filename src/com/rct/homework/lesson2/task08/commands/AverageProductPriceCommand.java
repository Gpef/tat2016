package com.rct.homework.lesson2.task08.commands;

import com.rct.homework.lesson2.task08.commands.options.CommandOptionsProvider;
import com.rct.homework.lesson2.task08.exceptions.StorageException;
import com.rct.homework.lesson2.task08.storage.Storage;

/**
 * Represents command that shows average price of
 * all products at the storage
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 11.10.2016
 */
public class AverageProductPriceCommand extends Command {
    private static final String NAME = "average-price";
    private static final int PARAMS = 0;

    /**
     * AverageProductPrice builder performs options validation
     * and creates a new AverageProductPriceCommand
     */
    public static class Builder extends Command.Builder {
        @Override
        public boolean canBuild(CommandOptionsProvider options) {
            if (!options.getCommand().equals(NAME) || options.getNumberOfOptions() != PARAMS){
                return false;
            }
            return true;
        }

        @Override
        public Command build(CommandOptionsProvider options) {
            canBuild(options);
            return new AverageProductPriceCommand();
        }
    }

    @Override
    public void execute(Storage storage) {
        try {
            System.out.println("Average product price at the storage: " + storage.findAveragePriceOfProducts());
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
    }
}

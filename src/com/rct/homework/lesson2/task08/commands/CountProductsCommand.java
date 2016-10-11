package com.rct.homework.lesson2.task08.commands;

import com.rct.homework.lesson2.task08.commands.options.CommandOptionsProvider;
import com.rct.homework.lesson2.task08.storage.Storage;

/**
 * Represents command that shows count of all
 * products at the storage
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 11.10.2016
 */
public class CountProductsCommand extends Command {
    private static final String NAME = "count-all";
    private static final int PARAMS = 0;

    /**
     * {@code CountProductsCommand} builder performs options validation
     * and creates a new {@code CountProductsCommand} command.
     */
    public static class Builder extends Command.Builder {

        @Override
        public boolean canBuild(CommandOptionsProvider options) {
            return !(!options.getCommand().equals(NAME) || options.getNumberOfOptions() != PARAMS);
        }

        @Override
        public Command build(CommandOptionsProvider options) {
            return new CountProductsCommand();
        }
    }

    @Override
    public void execute(Storage storage) {
        System.out.println("Products at storage: " + storage.getProductsAmount());
    }
}

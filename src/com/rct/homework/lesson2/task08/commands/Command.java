package com.rct.homework.lesson2.task08.commands;

import com.rct.homework.lesson2.task08.commands.options.CommandOptionsProvider;
import com.rct.homework.lesson2.task08.storage.Storage;

/**
 * Represents command that will be executed after
 * user's input.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 10.10.2016
 */
abstract public class Command {


    public abstract static class Builder {

        /**
         * Checks if builder can build command with specified
         * options.
         *
         * @param options options which specify command
         * @return true if the builder can build command with
         * specified options, false - otherwise
         */
        public abstract boolean canBuild(CommandOptionsProvider options);

        /**
         * Builds command based on specific options.
         *
         * @param options options which specify command
         * @return built command
         */
        public abstract Command build(CommandOptionsProvider options);
    }

    /**
     * Execute command depending on command implementation.
     *
     * @param storage storage to work with
     */
    public abstract void execute(Storage storage);
}

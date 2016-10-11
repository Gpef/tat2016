package com.rct.homework.lesson2.task08.commands.options;

import java.util.ArrayList;

/**
 * Provides options to build a command such as command name
 * and additional params, like products type
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 11.10.2016
 */
public abstract class CommandOptionsProvider {

    /**
     * @return command name
     */
    public abstract String getCommand();

    /**
     * @return array with additional params following main command name
     */
    public abstract ArrayList<String> getOptionalParams();

    /**
     * @return number of params inputted following command name
     */
    public abstract int getNumberOfOptions();

}


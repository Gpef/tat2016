package com.rct.homework.lesson2.task08.commands.options;

import java.util.ArrayList;

/**
 * Parses user input string and extract options from it
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 11.10.2016
 */
public class InputOptionsProvider extends CommandOptionsProvider {

    private String command;
    private ArrayList<String> optionalParams = new ArrayList<>();

    /**
     * Constructs an object by parsing user's input.
     * Parses string to lower case to avoid case incompatibility issues.
     *
     * @param userInput input to parse to options
     */
    public InputOptionsProvider(String userInput) {
        userInput = userInput.toLowerCase();
        String[] parsedOptions = userInput.split("\\s");
        command = parsedOptions[0];
        for (int i = 1; i < parsedOptions.length; i++) {
            optionalParams.add(parsedOptions[i]);
        }

    }

    @Override
    public int getNumberOfOptions() {
        return optionalParams.size();
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public ArrayList<String> getOptionalParams() {
        return optionalParams;
    }
}

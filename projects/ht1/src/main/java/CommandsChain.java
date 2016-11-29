import command.Command;
import command.CommandBuilder;
import exceptions.CommandException;
import exceptions.InvalidParamsException;
import io.reader.CommandReader;
import logging.CommandConverter;
import logging.LogWriter;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Main class of the framework. Executes commands chain
 * and logs their execution result into file.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 17.11.2016
 */
public class CommandsChain {
    private ArrayList<Command> commands;
    private LogWriter logWriter;

    private int passed = 0;
    private int failed = 0;
    private long timeSpend = 0;

    public CommandsChain(CommandReader commandReader, LogWriter output) throws CommandException {
        if ((null == commandReader) || (null == output)) {
            throw new CommandException("Can't create CommandsChain with null output file or command reader");
        }
        commands = getCommands(commandReader);
        logWriter = output;
    }

    /**
     * Execute all commands in the command chain and logs
     * result of their execution as exec time,
     * exec state(passed, failed) and information about
     * whole chain execution (total time spent, number of
     * tests, failed tests and passed tests, average exec
     * time).
     *
     * @param driver web driver that will be used to open urls
     *               and running tests on pages
     * @throws IOException if error occurred while logging results
     */
    public void run(WebDriver driver) throws IOException {
        CommandConverter commandConverter = new CommandConverter();
        logWriter.log(System.lineSeparator() + "# " + new Date(System.currentTimeMillis()) + " #");
        for (Command command : commands) {
            long startTime = new Date().getTime();
            boolean execResult = command.execute(driver);
            long execTime = new Date().getTime() - startTime;

            if (execResult) {
                passed++;
            } else {
                failed++;
            }
            timeSpend += execTime;

            try {
                logWriter.log(commandConverter.toLog(execResult, command.getName(), command.getPropertiesValues(),
                        execTime));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        logWriter.log("  Total tests: " + (failed + passed) + System.lineSeparator() +
                "  Passed/Failed : " + passed + "/" + failed + System.lineSeparator() +
                "  Total time: " + timeSpend / 1000d + System.lineSeparator() +
                "  Average time: " + timeSpend / (passed + failed) / 1000d);
    }

    /**
     * Gets commands list from {@code CommandReader} reader
     *
     * @param input reader to get commands from
     * @return list with read commands
     * @throws CommandException if error occurred while
     */
    private ArrayList<Command> getCommands(CommandReader input) throws CommandException {
        ArrayList<Command> commands = new ArrayList<>();
        CommandBuilder builder = new CommandBuilder().initDefaultBuilders();
        HashMap<String, String> nextArgs;
        while (input.hasNext()) {
            try {
                nextArgs = input.next();
                commands.add(builder.build(nextArgs));
            } catch (InvalidParamsException e) {
                System.out.println(e.getMessage() + ". Skipped");
            }
        }

        return commands;
    }
}

import io.reader.CommandReader;
import io.reader.TXTCommandReader;
import logging.TXTLogWriter;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

/**
 * Example of framework using.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 15.11.2016
 */
public class Main {
    private static final String CHROME_DRIVER_PATH = "D:\\Work\\QA\\WebDrivers\\chromedriver.exe";
    private static final String CHROME_DRIVER_PROPERY = "webdriver.chrome.driver";

    private static final String TXT_PATH = "commands.txt";
    private static final String JSON_PATH = "commands.json";
    private static final String XML_PATH = "commands.xml";


    public static void main(String[] args) {
        try {
            setDriver(CHROME_DRIVER_PROPERY, CHROME_DRIVER_PATH);
            CommandReader input = new TXTCommandReader(TXT_PATH);
            CommandsChain chain = new CommandsChain(input, new TXTLogWriter());
            chain.run(new ChromeDriver());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "| Cause: " + e.getCause());
        }
    }

    /**
     * Sets driver's property in System.properties if it not exists.
     *
     * @param driverProperty driver to setUp
     * @param driverPath     path to driver's executable
     */
    private static void setDriver(String driverProperty, String driverPath) {
        if (System.getProperty(driverProperty) == null) {
            File driverFile = new File(driverPath);
            System.setProperty(driverProperty, driverFile.getAbsolutePath());
        }
    }
}


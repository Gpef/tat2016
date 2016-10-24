package homework.task08;

import homework.task08.exceptions.ProductException;
import homework.task08.exceptions.StorageException;
import homework.task08.storage.Storage;

/**
 * Main class of the application.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 08.10.2016
 */
public class Main {

    /**
     * Entrance point to the application.
     * Starts working from requesting information about new product to add.
     * If user wants to continue adding products, he just inputs 'add'. If user
     * inputs any other string, program will go to the command mode. Application
     * will work in endless cycle and will quit only if user inputs "quit"
     * command.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Storage storage = new Storage();
        Dialogs dialogs = new Dialogs();
        try {
            dialogs.start(storage);
        } catch (ProductException | StorageException e) {
            System.out.println(e.getMessage());
        }

    }
}

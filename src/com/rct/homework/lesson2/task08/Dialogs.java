package com.rct.homework.lesson2.task08;

import com.rct.homework.lesson2.task08.commands.*;
import com.rct.homework.lesson2.task08.commands.options.CommandOptionsProvider;
import com.rct.homework.lesson2.task08.commands.options.InputOptionsProvider;
import com.rct.homework.lesson2.task08.exceptions.InvalidParamsNumberException;
import com.rct.homework.lesson2.task08.exceptions.ProductException;
import com.rct.homework.lesson2.task08.exceptions.StorageException;
import com.rct.homework.lesson2.task08.storage.Product;
import com.rct.homework.lesson2.task08.storage.Storage;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Class for dialogs with user and showing messages and errors
 * while inputting
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 11.10.2016
 */
public class Dialogs {
    private static final String COMMAND_WAITING_SYMBOL = ">";
    private static final String HELP = "supported commands:" +
            "\naverage-price" +
            "\naverage-price 'type' | where 'type' is type of product" +
            "\ncount-all" +
            "\ncount-types" +
            "\nquit";
    private static final String NEW_PRODUCT = "Adding new products";
    private static final String ADD_MORE_PRODUCTS = "Type 'add' to continue adding mode. " +
            "Type something else to go to command mode";
    private static final String PRODUCT_NAME = "Product name:";
    private static final String PRODUCT_TYPE = "Product type:";
    private static final String PRODUCT_PRICE = "Product price:";
    private static final String PRODUCT_AMOUNT = "Amount to add:";

    /**
     * Main function of dialog controller. Performs adding products to storage
     * and switching to command mode
     *
     * @param storage storage to work with
     * @throws ProductException if error occurred with parsing price to number
     */
    public void start(Storage storage) throws ProductException, StorageException {
        while (true) {
            addProductMode(storage);
            String response = getString(ADD_MORE_PRODUCTS);
            if (!response.equals("add")) {
                break;
            }
        }
        commandMode(storage);
    }

    /**
     * Shows message to user and string from input
     *
     * @param message message to show before getting input
     * @return string got from user input
     */
    private String getString(String message) {
        System.out.print(message + "\n" + COMMAND_WAITING_SYMBOL + " ");
        return new Scanner(System.in).nextLine();
    }

    /**
     * Product adding mode
     */
    private void addProductMode(Storage storage) throws ProductException, StorageException {
        System.out.println(NEW_PRODUCT);
        String type = getString(PRODUCT_TYPE);
        String name = getString(PRODUCT_NAME);

        BigDecimal price = getValidPrice();
        BigInteger amount = getValidAmount();
        storage.add(new Product(type, name, price), amount);
    }

    /**
     * Command mode where user inputs commands in console
     */
    private void commandMode(Storage storage) {
        System.out.println("command mode:");

        while (true) {
            try {
                System.out.print(COMMAND_WAITING_SYMBOL + " ");
                String userInput = new Scanner(System.in).nextLine();
                if (userInput.equals("")) {
                    continue;
                }
                CommandOptionsProvider options = new InputOptionsProvider(userInput);
                Command command = (new CommandBuilder().
                        add(new AverageProductPriceCommand.Builder()).
                        add(new AverageTypePriceCommand.Builder()).
                        add(new QuitCommand.Builder()).
                        add(new CountProductsCommand.Builder()).
                        add(new CountTypesCommand.Builder()).build(options));
                command.execute(storage);
            } catch (InvalidParamsNumberException e) {
                System.out.println(e.getMessage());
                showHelp();
            }
        }
    }

    /**
     * Gets input from user and checks if it's valid
     *
     * @return valid price
     */
    private BigDecimal getValidPrice() {
        String priceString;
        while (true) {
            try {
                priceString = getString(PRODUCT_PRICE);

                if (Validator.isValidPrice(priceString)) {
                    return new BigDecimal(priceString);
                }
            } catch (ProductException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Gets input from user and checks if it's valid
     * Grants only valid value to be returned
     *
     * @return valid amount
     */
    private BigInteger getValidAmount() {
        String amount;
        while (true) {
            try {
                amount = getString(PRODUCT_AMOUNT);
                if (Validator.isValidAmount(amount)) {
                    break;
                }
            } catch (ProductException e) {
                System.out.println(e.getMessage());
            }
        }
        return new BigInteger(amount);
    }

    /**
     * Show commands help in console
     */
    private void showHelp() {
        System.out.println(HELP);
    }
}

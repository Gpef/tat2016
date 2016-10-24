package homework.task07;

import homework.task07.localisation.MessageStrings;
import homework.task07.validator.*;

import java.util.ArrayList;

/**
 * Entrance point to the program.
 * Gets inputted string and validates
 * for several rules.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 06-10-2016
 */
public class Main {

    /**
     * Entrance point to the program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println(MessageStrings.HELLO_MESSAGE);
        String stringToValidate = InputController.getInput();
        ArrayList<Validator> validators = initValidators();
        for (Validator validator : validators) {
            if (validator.validate(stringToValidate)) {
                System.out.println(validator.getPassMessage());
            }
        }
    }

    /**
     * Initialises validators to perform checks.
     *
     * @return initialised <code>ArrayList</code> of validators
     */
    private static ArrayList<Validator> initValidators() {
        ArrayList<Validator> validators = new ArrayList<>();
        validators.add(new DictionaryValidator());
        validators.add(new NoDigitValidator());
        validators.add(new OnlyDigitsValidator());
        validators.add(new WordsValidator());
        return validators;
    }
}
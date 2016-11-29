package app;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 28.11.2016
 */
public class Validator {

    /**
     * Validates FML and generates error message for invalid data.
     *
     * @param person person to validate FML
     * @return error message if person has invalid data,
     * empty string if no invalid data found
     */
    public String validatePersonFMLName(Person person) {
        String error_message = "";
        if (!person.validateFMLNamePart(person.getName(), false)) {
            error_message += "Имя должно быть строкой от 1 до 150 символов из букв, цифр, знаков подчёркивания и знаков минус.<br />";
        }

        if (!person.validateFMLNamePart(person.getSurname(), false)) {
            error_message += "Фамилия должна быть строкой от 1 до 150 символов из букв, цифр, знаков подчёркивания и знаков минус.<br />";
        }

        if (!person.validateFMLNamePart(person.getMiddlename(), true)) {
            error_message += "Отчество должно быть строкой от 0 до 150 символов из букв, цифр, знаков подчёркивания и знаков минус.<br />";
        }

        return error_message;
    }

    /**
     * Validates phone number and generates error message for invalid data.
     *
     * @param phone phone to validate number
     * @return error message if phone has invalid data,
     * empty string if no invalid data found
     */
    public String validateNumber(Phone phone) {
        String errorMessage = "";
        if (!phone.validateNumber()) {
            errorMessage += "Номер должен содержать от 2 до 50 цифр, может начинать с + и содержать -,#";
        }

        return errorMessage;
    }
}

package app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents Person entity from database. Can create object with getting data from database.
 * Has method to validate data.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 27.11.2016
 */
public class Phone {

    private String number = "";
    private String id = "";
    private String ownerId = "";

    public Phone() {
        this.id = "0";
        this.number = "";
        this.ownerId = "";
    }

    public Phone(String ownerId, String number) {
        this.id = "0";
        this.number = number;
        this.ownerId = ownerId;
    }

    public Phone(String id, String ownerId, String number) {
        this.id = id;
        this.number = number;
        this.ownerId = ownerId;
    }

    /**
     * Validates number to correspond pattern. Number can
     * hav +(at the beginning), # and - and 2 to 50 digits.
     *
     * @return empty string - if number corresponds pattern,
     * error message - otherwise.
     */
    public boolean validateNumber() {
        if (number == null || number.equals("")) {
            return false;
        }
        Matcher matcher = Pattern.compile("[(0-9)+#-]{2,50}").matcher(number);
        return matcher.matches();
    }

    public String getNumber() {
        return number;
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setId(String id) {
        this.id = id;
    }
}

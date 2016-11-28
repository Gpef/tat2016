package app;

import util.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents Person entity from database. Can create object with getting data from database.
 * Has method to validate data.
 */
public class Person {
    private String id = "";
    private String name = "";
    private String surname = "";
    private String middlename = "";
    private HashMap<String, Phone> phones = new HashMap<>();

    /**
     * Constructor for {@code Person}. Set default data as id, name, surname and
     * middlename. If person with that id has phone numbers in data base, they'r set in
     * phones map. If there is no phones - phones map will be null.
     *
     * @param id         id in database
     * @param name       name in database
     * @param surname    surname (last name) in database
     * @param middlename middle name (father's name) in database
     */
    public Person(String id, String name, String surname, String middlename) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;

        ResultSet resultSet = DBWorker.getInstance().getDBData("SELECT * FROM `phone` WHERE `owner`=" + id);
        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    try {
                        String phoneId = resultSet.getString("id");
                        this.phones.put(phoneId, new Phone(phoneId, id, resultSet.getString("number"))
                        );
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates empty Person record
     */
    public Person() {
        this.id = "0";
        this.name = "";
        this.surname = "";
        this.middlename = "";
    }

    /**
     * Constructor for record that will be added to database.
     *
     * @param name       person's name
     * @param surname    person's last name
     * @param middlename person's middlename
     */
    public Person(String name, String surname, String middlename) {
        this.id = "0";
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
    }

    /**
     * Validate parts of FML (first name, last name, middle name). If emptyAllowed param is true,
     * that part of FIO can be empty.
     *
     * @param fmlNamePart  part of full name to validate
     * @param emptyAllowed true - if this part of name can be empty (middlename for example)
     * @return true - if name is valid, false - otherwise
     */
    public boolean validateFMLNamePart(String fmlNamePart, boolean emptyAllowed) {
        if (emptyAllowed) {
            Matcher matcher = Pattern.compile("[\\w-[а-яА-ЯёЁ]&&[^0-9]]{0,150}").matcher(fmlNamePart);
            return matcher.matches();
        } else {
            Matcher matcher = Pattern.compile("[\\w-[а-яА-ЯёЁ]&&[^0-9]]{1,150}").matcher(fmlNamePart);
            return matcher.matches();
        }
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    /**
     * @return middle name if exists. If it's null (allowed to be empty), that
     * returns empty string
     */
    public String getMiddlename() {
        if ((this.middlename != null) && (!this.middlename.equals("null"))) {
            return this.middlename;
        } else {
            return "";
        }
    }

    /**
     * @return full name with first name, last name and middle name
     */
    public String getFML() {
        return (null == surname ? "" : surname) + " " +
                (null == name ? "" : name) + " " +
                (null == middlename ? "" : middlename);
    }

    public HashMap<String, Phone> getPhones() {
        return this.phones;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setPhones(HashMap<String, Phone> phones) {
        this.phones = phones;
    }
}

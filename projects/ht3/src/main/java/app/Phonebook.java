package app;

import util.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Represents entity that contains persons profiles with their FML and phones.
 * Can access database to change persons and phones data.
 */
public class Phonebook {
    private HashMap<String, Person> persons = new HashMap<>();
    private final DBWorker dbWorker = DBWorker.getInstance();
    private static Phonebook instance = null;

    /**
     * Phonebook realises singleton pattern. This method returns instance of Phonebook.
     * If it doesn't exist, creates new instance and returns it.
     *
     * @return instance of {@code Phonebook} singleton.
     * @throws ClassNotFoundException if driver class wasn't found
     * @throws SQLException           if error occurred while working with database
     */
    public static Phonebook getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new Phonebook();
        }

        return instance;
    }

    /**
     * Constructors collects all persons from database and puts them into persons hash map.
     *
     * @throws SQLException           if error occurred while working with database
     */
    protected Phonebook() throws SQLException {
        ResultSet resultSet = this.dbWorker.getDBData("SELECT * FROM `person` ORDER BY `surname` ASC");
        while (resultSet.next()) {
            this.persons.put(
                    resultSet.getString("id"),
                    new Person(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getString("middlename"))
            );
        }
    }

    /**
     * Adds information about person to database.
     *
     * @param person person to add
     * @return true - if person was added correctly,
     * false - if it wasn't added.
     */
    public boolean addPerson(Person person) {
        ResultSet resultSet;
        String query;

        if (!person.getSurname().equals("")) {
            query = "INSERT INTO `person` (`name`, `surname`, `middlename`) VALUES ('" +
                    person.getName() + "', '" +
                    person.getSurname() + "', '" +
                    person.getMiddlename() + "')";
        } else {
            query = "INSERT INTO `person` (`name`, `surname`) VALUES ('" +
                    person.getName() + "', '" +
                    person.getSurname() + "')";
        }

        Integer affectedRows = this.dbWorker.changeDBData(query);
        if (affectedRows > 0) {
            person.setId(this.dbWorker.getLastInsertId().toString());
            this.persons.put(person.getId(), person);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates person's information in database.
     *
     * @param person new person information that need to be added
     * @return true - if person was updated correctly,
     * false - if it wasn't changed.
     */
    public boolean updatePerson(Person person) {
        Integer personId = Integer.parseInt(person.getId());
        String query;

        if (!person.getSurname().equals("")) {
            query = "UPDATE `person` SET `name` = '" +
                    person.getName() + "', `surname` = '" +
                    person.getSurname() + "', `middlename` = '" +
                    person.getMiddlename() + "' WHERE `id` = " +
                    personId;
        } else {
            query = "UPDATE `person` SET `name` = '" +
                    person.getName() + "', `surname` = '" +
                    person.getSurname() + "' WHERE `id` = " +
                    personId;
        }

        Integer affected_rows = this.dbWorker.changeDBData(query);

        if (affected_rows > 0) {
            this.persons.put(person.getId(), person);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes person's information in database if person with
     * specified id exists in database.
     *
     * @param id id of person that need to be deleted
     * @return true - if person was deleted correctly,
     * false - if it wasn't deleted or found.
     */
    public boolean deletePerson(String id) {
        if ((id != null) && (!id.equals("null"))) {
            int parsedId = Integer.parseInt(id);
            Integer affectedRows = this.dbWorker.changeDBData("DELETE FROM `person` WHERE `id`=" + parsedId);

            if (affectedRows > 0) {
                this.persons.remove(id);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Adds information about phone to database.
     *
     * @param phone phone to add
     * @return true - if phone was added correctly,
     * false - if it wasn't added.
     */
    public boolean addPhone(Phone phone) {
        String query = "INSERT INTO `phone` (`owner`, `number`) VALUES ('" +
                phone.getOwnerId() + "', '" +
                phone.getNumber() + "')";
        Integer affectedRows = this.dbWorker.changeDBData(query);
        if (affectedRows > 0) {
            phone.setId(this.dbWorker.getLastInsertId().toString());

            try {
                this.persons.get(phone.getOwnerId()).getPhones().put(phone.getId(), phone);
            } catch (Exception e) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates information  about phone in database.
     *
     * @param phone new phone information that need to be added
     * @return true - if phone was updated correctly,
     * false - if it wasn't changed.
     */
    public boolean updatePhone(Phone phone) {
        Integer phoneId = Integer.parseInt(phone.getId());
        String query = "UPDATE `phone` SET `number` = '" +
                phone.getNumber() + "' WHERE `id` = " +
                phoneId;

        Integer affectedRows = this.dbWorker.changeDBData(query);
        if (affectedRows > 0) {
            try {
                this.persons.get(phone.getOwnerId()).getPhones().put(phone.getId(), new Phone(phone.getId(), phone
                        .getOwnerId(), phone.getNumber()));
            } catch (Exception e) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes information about phone in database if phone with that id
     * exists in database.
     *
     * @param id      id of phone that need to be deleted
     * @param ownerId id of person who owns this number
     * @return true - if phone was deleted correctly,
     * false - if it wasn't deleted or found.
     */
    public boolean deletePhone(String id, String ownerId) {
        if ((id != null) && (!id.equals("null"))) {
            int phoneId = Integer.parseInt(id);
            Integer affectedRows = this.dbWorker.changeDBData("DELETE FROM `phone` WHERE `id`=" + phoneId);

            if (affectedRows > 0) {
                this.persons.get(ownerId).getPhones().remove(id);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public HashMap<String, Person> getContents() {
        return persons;
    }

    /**
     * @param id id of person to get
     * @return person with specified id or null, if person with
     * that id doesn't exist
     */
    public Person getPerson(String id) {
        return this.persons.get(id);
    }

    /**
     * @param ownerId id of person who owns phone
     * @param id      id of phone to get
     * @return phone with specified id and ownerId or null, if phone with
     * that id and ownerId doesn't exist
     */
    public Phone getPhone(String ownerId, String id) {
        return getPerson(ownerId).getPhones().get(id);
    }
}

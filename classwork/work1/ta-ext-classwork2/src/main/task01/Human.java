package main.task01;

/**
 * Human entity. Can do nothing but contains first and last name and age.
 * Human won't be created if age is < 0.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 21.10.2016
 */
public class Human {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    private int age;

    /**
     * Human constructor that checks if age is < 0, Human won't
     * be created then.
     *
     * @param firstName first name of human
     * @param lastName  last name of human
     * @param age       age of human
     * @throws Exception if age is < 0
     */
    public Human(String firstName, String lastName, int age) throws Exception {
        if (!checkAge(age)) {
            throw new Exception("Age can't be < 0");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * Verifies that human age is < 0.
     *
     * @return {@code true} if age is > 0, {@code false} - otherwise
     */
    private boolean checkAge(int age) {
        return age > 0;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + age;
    }

}

package banking;

/**
 * The banking.Profile class holds the information regarding a person who has a bank account
 * Full name and date of birth are part of their profile
 *
 * @author Vishal Saravanan, Yining Chen
 */

public class Profile implements Comparable<Profile>{
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    /**
     * Constructor for a Profile object
     *
     * @param firstName String representation of the holder's first name
     * @param lastName String representation of the holder's last name
     * @param dateOfBirth Date object that holds the month, date, and year of holder's birthday
     */
    public Profile(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the first name of the holder
     *
     * @return String representation of the holder's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the holder
     *
     * @return String representation of the holder's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the birthdate of the holder
     *
     * @return Date object that holds the month, date, and year of holder's birthday
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Compares two Profile objects
     * Concatenates last name to first name of each Profile object before comparing
     * If names are the same, compares the date of birth next
     *
     * @param other the Profile object to be compared to
     * @return 0 if their names are the same,
     * negative integer if first full name/date of birth is less than second
     * positive integer if first full name/date of birth is greater than second
     */
    @Override
    public int compareTo(Profile other) {
        String name = this.firstName + this.lastName;
        String otherName = other.firstName + other.lastName;
        int nameComparison = name.compareTo(otherName); //String compareTo() method
        if (nameComparison != 0) {
            return nameComparison;
        }
        return this.dateOfBirth.compareTo(other.dateOfBirth);
    }

    /**
     * Converts Profile to a string that can be printed
     *
     * @return a string that contains the first name, last name, and date of birth of an account holder
     */
    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " " + this.dateOfBirth;
    }

    /**
     * Compares two banking.Profile objects for equality
     *
     * @param obj other banking.Profile being checked for equality
     * @return true if they are the same object: first name and last name are the same
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile other = (Profile) obj;
            return this.firstName.equalsIgnoreCase(other.firstName) && this.lastName.equalsIgnoreCase(other.lastName) && this.dateOfBirth.equals(other.dateOfBirth);
        }
        return false;
    }
}

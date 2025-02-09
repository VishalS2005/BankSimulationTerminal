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
    private Date dateOfBirth; //date of birth

    public Profile(String firstName, String lastName, Date dateOfBirth) { //constructor
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Compares this banking.Profile to another banking.Profile based on their full names
     * Concatenates the first and last names of both profile and compares the resulting strings.
     *
     * @param other banking.Profile being compared with
     * @return 0 if the full names are equal,
     * a negative integer if this banking.Profile's full name comes before the other banking.Profile's full name,
     * or a positive integer if this banking.Profile's full name comes after the other banking.Profile's full name
     */
    @Override
    public int compareTo(Profile other) {
        String name = this.firstName + this.lastName;
        String otherName = other.firstName + other.lastName;
        return name.compareTo(otherName);
    }

    /**
     * Converts banking.Profile to a string that can be printed
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
            return this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName);
        }
        return false;
    }

    /** Test bed*/
    public static void main(String[] args) {

    }
}

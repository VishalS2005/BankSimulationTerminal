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


    @Override
    public int compareTo(Profile other) {
        // Compare first by full name
        String name = this.firstName + this.lastName;
        String otherName = other.firstName + other.lastName;
        int nameComparison = name.compareTo(otherName);

        // If names are not equal, return the comparison result
        if (nameComparison != 0) {
            return nameComparison;
        }

        // If names are equal, compare by date of birth
        return this.dateOfBirth.compareTo(other.dateOfBirth);
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
            return this.firstName.equalsIgnoreCase(other.firstName) && this.lastName.equalsIgnoreCase(other.lastName) && this.dateOfBirth.equals(other.dateOfBirth);
        }
        return false;
    }

    /** Test bed*/
    public static void main(String[] args) {

    }
}

package banking;

/**
 * The banking.Profile class holds the information regarding a person who has a bank account
 * Full name and date of birth are part of their profile
 *
 * @author Vishal Saravanan, Yining Chen
 */

public class Profile implements Comparable<Profile>{
    private String fname; //full name
    private String lname; //last name
    private Date dob; //date of birth

    public Profile(String fname, String lname, Date dob) { //constructor
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
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
        String name = this.fname + this.lname;
        String otherName = other.fname + other.lname;
        return name.compareTo(otherName);
    }

    /**
     * Converts banking.Profile to a string that can be printed
     *
     * @return a string that contains the first name, last name, and date of birth of an account holder
     */
    @Override
    public String toString() {
        return this.fname + " " + this.lname + " " + this.dob;
    }

    /**
     * Compares two banking.Profile objects for equality
     *
     * @param obj other banking.Profile being checked for equality
     * @return true if they are the same object: first name, last name, and date of birth are all the same
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile other = (Profile) obj;
            return this.fname.equals(other.fname)      //String .equals
                    && this.lname.equals(other.lname)  //String .equals
                    && this.dob.equals(other.dob);     //uses .equals from banking.Date class
        }
        return false;
    }
}

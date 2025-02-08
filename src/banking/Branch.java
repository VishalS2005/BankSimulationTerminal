package banking;

/**
 * The banking.Branch enum class holds the information regarding all the different types of banking.Branch locations
 * Each banking.Branch object has a 5-digit ZIP code, 3-digit branch code, and the county the branch is located in
 *
 * @author Vishal Saravanan, Yining Chen
 */

public enum Branch {
    EDISON("08817", "100", "Middlesex"),
    BRIDGEWATER("08807", "200", "Somerset"),
    PRINCETON("08542", "300", "Mercer"),
    PISCATAWAY("08854", "400", "Middlesex"),
    WARREN("07057", "500", "Somerset");

    private final String zip; //5-digit number that represents the ZIP code
    private final String branchCode; //3-digit number that represents the branch code
    private final String county; //String that represents the county the branch is located in

    Branch(String zip, String branchCode, String county) { //constructor
        this.zip = zip;
        this.branchCode = branchCode;
        this.county = county;
    }

    /**
     * Converts banking.Branch to a string that can be printed
     *
     * @return the 3-digit branch code as a String
     */
    @Override
    public String toString() {
        return branchCode;
    }
}

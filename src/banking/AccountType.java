package banking;

/**
 * The AccountType class holds the information regarding all the different types of accounts that can be opened at the bank
 * Each two-digit account type represents a different type
 *
 * @author Vishal Saravanan, Yining Chen
 */

public enum AccountType {
    CHECKING("01"),
    SAVINGS("02"),
    MONEY_MARKET("03");

    private final String code; //2-digit code that represents the type of account

    AccountType(String code) {
        this.code = code;
    } //constructor

    /**
     * Returns a 2-digit String representation of the AccountType
     * Checking(01), Savings(02), and Money_Market(03)
     *
     * @return 2-digit String representation of the AccountType
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Converts AccountType to a string that can be printed
     *
     * @return the 2-digit account type as a String
     */
    @Override
    public String toString() {
        return name();
    }
}

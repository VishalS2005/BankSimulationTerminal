package banking;

/**
 * The AccountType class holds the information regarding all the different types of accounts that can be opened at the bank.
 * Each two-digit account type represents a different type.
 *
 * @author Vishal Saravanan, Yining Chen
 */

public enum AccountType {
    /**
     * Checking account - 01.
     */
    CHECKING("01"),

    /**
     * Savings account - 02.
     */
    SAVINGS("02"),

    /**
     * Money market account - 03.
     */
    MONEY_MARKET("03"),

    COLLEGE_CHECKING("04"),

    CD("05");

    /**
     * 2-digit code that represents the type of account
     */
    private final String code;

    /**
     * Creates an AccountType object.
     * The three types of accounts are: Checking(01), Savings(02), and Money_Market(03).
     *
     * @param code String representation of the 2-digit account type
     */
    AccountType(String code) {
        this.code = code;
    }

    /**
     * Returns a 2-digit String representation of the AccountType.
     * The three types of accounts are: Checking(01), Savings(02), and Money_Market(03).
     *
     * @return 2-digit String representation of the AccountType
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Converts AccountType to a string that can be printed.
     *
     * @return the 2-digit account type as a String
     */
    @Override
    public String toString() {
        return name();
    }
}

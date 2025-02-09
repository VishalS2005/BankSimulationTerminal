package banking;

/**
 * The banking.AccountType class holds the information regarding all the different types of accounts that can be opened at the bank
 * Each two-digit account type represents a different type
 *
 * @author Vishal Saravanan, Yining Chen
 */

public enum AccountType {
    CHECKING("01"),
    REGULAR_SAVINGS("02"),
    MONEY_MARKET_SAVINGS("03");

    private final String code; //2-digit code that represents the type of account

    AccountType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * Converts banking.AccountType to a string that can be printed
     *
     * @return the 2-digit account type as a String
     */
    @Override
    public String toString() {
        return name();
    }
}

package banking;

import java.text.DecimalFormat;

/**
 * The Account class holds the information regarding a bank account
 * The account number, Profile object of the holder, and the balance are held in each account
 *
 * @author Vishal Saravanan, Yining Chen
 */

public class Account implements Comparable<Account> {

    private AccountNumber number; //AccountNumber object with information about 9-digit number that represents bank account

    private Profile holder; //Profile object with information about account holder

    private double balance; //amount of money currently in bank account

    private static final DecimalFormat df = new DecimalFormat("#,##0.00"); //formatted in a readable manner for money

    /**
     * Constructor for an Account object
     *
     * @param branch 3-digit integer representation of a Branch
     * @param type 2-digit integer representation of the type of Account
     * @param holder Profile object that represents the full name and date of birth of an Account holder
     */
    public Account(Branch branch, AccountType type, Profile holder) {
        this.number = new AccountNumber(branch, type);
        this.holder = holder;
    }

    /**
     * Constructor for an Account object
     *
     * @param number 9-digit account number
     */
    public Account(AccountNumber number) {
        this.number = number;
        this.holder = null;
    }

    /**
     * Takes money out of the account and updates the balance of an account
     *
     * @param amount quantity of money deducted from the account
     */
    public void withdraw(double amount) { //to update the balance
        this.balance -= amount;
    }

    /**
     * Puts money into the account and updates the balance of an account
     *
     * @param amount quantity of money added to the account
     */
    public void deposit(double amount) { //to update the balance
        this.balance += amount;
    }

    /**
     * Account's balance parameter is changed to 0
     */
    public void emptyBalance() {
        this.balance = 0;
    }

    /**
     * Returns the AccountNumber object associated with this Account.
     *
     * @return the AccountNumber object of this Account
     */
    public AccountNumber getAccountNumber() {
        return this.number;
    }

    /**
     * Returns a double value that represents the balance of an Account
     *
     * @return balance of an Account
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Returns a 2-digit AccountType object representation of the AccountType
     * Checking(01), Savings(02), and Money_Market(03)
     *
     * @return AccountType of the account
     */
    public AccountType getType() {
        return this.number.getType();
    }

    /**
     * Returns a string that represents the first name in an Account
     *
     * @return first name of an Account
     */
    public String getFirstName() {
        return this.holder.getFirstName();
    }

    /**
     * Returns a string that represents the last name in an Account
     *
     * @return last name of an Account
     */
    public String getLastName() {
        return this.holder.getLastName();
    }

    /**
     * Returns a date of birth of Account holder
     *
     * @return Date object representation of date of birth
     */
    public Date getDateOfBirth() {
        return this.holder.getDateOfBirth();
    }

    /**
     * Changes the 2-digit AccountType object representation of the AccountType to parameter input 'type'
     * Checking(01), savings(02), or money market account(03)
     *
     * @param type 2-digit AccountType object representation of account type
     */
    public void setAccountType(AccountType type) {
        this.number.setType(type);
    }

    /**
     * Compares two accounts by the AccountType parameter
     *
     * @param other Account being compared to
     * @return 0 if the AccountType of both accounts are the same,
     * -1 if first AccountType is less than second,
     * 1 if first AccountType is greater than second
     */
    public int compareByAccountType(Account other) {
        int cmp = this.number.getType().compareTo(other.number.getType());
        if (cmp != 0) {
            return cmp;
        }
        return this.getAccountNumber().compareTo(other.getAccountNumber());
    }

    /**
     * Compares two accounts by the Branch parameter
     * First checks if the Accounts are the same county before checking the Branch
     * Comparison is case-insensitive
     *
     * @param other Account being compared to
     * @return 0 if the Branch of both accounts are the same,
     * -1 if first Branch is less than second,
     * 1 if first Branch is greater than second
     */
    public int compareByBranch(Account other) {
        //gets the String representation of county based on the AccountNumber of the holder
        String countyA = this.getAccountNumber().getBranch().getCounty();
        String countyB = other.getAccountNumber().getBranch().getCounty();

        int countyComparison = countyA.compareToIgnoreCase(countyB);
        if (countyComparison != 0) {
            return countyComparison;
        }
        //County is the same, so now compare the Branch

        String cityA = this.getAccountNumber().getBranch().name();
        String cityB = other.getAccountNumber().getBranch().name();
        return cityA.compareToIgnoreCase(cityB);
    }

    /**
     * Compares the name and date of birth of two accounts
     * First checks the first name, last name, and date of birth and returns if not equal to 'other'
     *
     *
     * @param other AccountNumber being compared with
     * @return 0 if AccountNumber are equal,
     * negative integer if first AccountNumber is less than AccountNumber,
     * positive integer if first AccountNumber is greater than AccountNumber
     */
    @Override
    public int compareTo(Account other) {
        int cmp = this.getLastName().compareToIgnoreCase(other.getLastName());
        if (cmp != 0) {
            return cmp;
        }
        // Compare first names, ignoring case
        cmp = this.getFirstName().compareToIgnoreCase(other.getFirstName());
        if (cmp != 0) {
            return cmp;
        }
        // Compare dates of birth
        cmp = this.getDateOfBirth().compareTo(other.getDateOfBirth());
        if (cmp != 0) {
            return cmp;
        }
        // Compare account numbers numerically.
        int aNumber = Integer.parseInt(this.getAccountNumber().toString());
        int bNumber = Integer.parseInt(other.getAccountNumber().toString());
        return aNumber - bNumber;
    }

    /**
     * Converts Account to a string that can be printed
     *
     * @return a string that contains the Account Number, the Account Holder, and the Account Balance
     */
    @Override
    public String toString() {
        return "Account#[" + this.number + "] Holder[" + this.holder + "] Balance[$" + df.format(this.balance) + "] Branch[" + this.getAccountNumber().getBranch() + "]";
    }

    /**
     * Compares two Account objects for equality
     *
     * @param obj other Account being checked for equality
     * @return true if they are the same object: Account object is compared using the Account Number
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account other = (Account) obj;
            return other.number.equals(this.number) || (other.holder.equals(this.holder) && this.number.getType() == other.number.getType());
        }
        return false;
    }
}

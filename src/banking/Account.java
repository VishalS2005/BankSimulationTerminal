package banking;

import java.text.DecimalFormat;

/**
 * The banking.Account class holds the information regarding a bank account
 * The account number, banking.Profile object of the holder, and the balance are held in each account
 *
 * @author Vishal Saravanan, Yining Chen
 */

public class Account implements Comparable<Account> {

    private AccountNumber number; //banking.AccountNumber object with information about 9-digit number that represents bank account

    private Profile holder; //banking.Profile object with information about account holder

    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    private double balance; //amount of money currently in bank account

    public Account(Branch branch, AccountType type, Profile holder, double balance) {
        this.number = new AccountNumber(branch, type);
        this.holder = holder;
        this.balance = balance;
    }

    public Account(Branch branch, AccountType type, Profile holder) {
        this.number = new AccountNumber(branch, type);
        this.holder = holder;
    }

    public Account(AccountNumber number) {
        this.number = number;
        this.holder = null;
    }

    public double getBalance() {
        return balance;
    }

    public AccountType getType() {
        return number.getType();
    }

    public void setAccountType(AccountType type) {
        number.setType(type);
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
     * Returns the banking.AccountNumber associated with this banking.Account.
     *
     * @return the banking.AccountNumber of this banking.Account
     */
    public AccountNumber getAccountNumber() {
        return this.number;
    }

    public String getFirstName() {
        return this.holder.getFirstName();
    }

    public String getLastName() {
        return this.holder.getLastName();
    }

    public Date getDateOfBirth() {
        return this.holder.getDateOfBirth();
    }

    public int compareByAccountType(Account other) {
        int cmp = this.number.getType().compareTo(other.number.getType());
        if (cmp != 0) {
            return cmp;
        }
        return this.getAccountNumber().compareTo(other.getAccountNumber());
    }

    public int compareByBranch(Account other) {
        String countyA = this.getAccountNumber().getBranch().getCounty();
        String countyB = other.getAccountNumber().getBranch().getCounty();

        int countyComparison = countyA.compareToIgnoreCase(countyB);
        if (countyComparison != 0) {
            return countyComparison;
        }

        String cityA = this.getAccountNumber().getBranch().name();
        String cityB = other.getAccountNumber().getBranch().name();
        return cityA.compareToIgnoreCase(cityB);
    }

    public void emptyBalance() {
        this.balance = 0;
    }

    /**
     * Compares the banking.Account Numbers of two accounts
     *
     * @param other banking.Account being compared with
     * @return 0 if banking.Account Numbers are equal,
     * -1 if first account is less than second account,
     * 1 if first account is greater than second account
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
     * Converts banking.Account to a string that can be printed
     *
     * @return a string that contains the banking.Account Number, the banking.Account Holder, and the banking.Account Balance
     */
    @Override
    public String toString() {
        return "Account#[" + this.number + "] Holder[" + this.holder + "] Balance[$" + df.format(this.balance) + "] Branch[" + this.getAccountNumber().getBranch() + "]";
    }

    /**
     * Compares two banking.Account objects for equality
     *
     * @param obj other banking.Account being checked for equality
     * @return true if they are the same object: banking.Account object is compared using the banking.Account Number
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

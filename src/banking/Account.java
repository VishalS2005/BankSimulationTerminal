package banking;

/**
 * The banking.Account class holds the information regarding a bank account
 * The account number, banking.Profile object of the holder, and the balance are held in each account
 *
 * @author Vishal Saravanan, Yining Chen
 */

public class Account implements Comparable<Account> {

    private AccountNumber number; //banking.AccountNumber object with information about 9-digit number that represents bank account

    private Profile holder; //banking.Profile object with information about account holder

    /**
     * The amount of money in the checking/savings/money market account
     * Money market savings accounts must maintain a minimum balance of $2,000.
     * If the account balance falls under the minimum, the account will be downgraded to a regular savings account.
     */
    private double balance; //amount of money currently in bank account


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
        if (this.balance > other.balance)
            return 1;
        if (this.balance < other.balance)
            return -1;
        return 0;
    }

    /**
     * Converts banking.Account to a string that can be printed
     *
     * @return a string that contains the banking.Account Number, the banking.Account Holder, and the banking.Account Balance
     */
    @Override
    public String toString() {
        return "banking.Account#[" + this.number + "] Holder[" + this.holder + "] Balance[" + this.balance + "]";
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
            return other.number == this.number;
        }
        return false;
    }
}

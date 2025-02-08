package banking;

/**
 * banking.AccountNode class used for the banking.Archive to store closed Accounts in a linked list implementation
 *
 @author Vishal Saravanan, Yining Chen
 */

public class AccountNode {
    private AccountNode next; //pointer to the next banking.AccountNode
    private Account Account;  //banking.Account object with account number, profile of holder, and balance

    public AccountNode() { //constructor
        next = null;
        Account = null;
    }

    public AccountNode(Account account) { //constructor
        this.Account = account;
    }

    public AccountNode(Account account, AccountNode next) { //constructor
        this.Account = account;
        this.next = next;
    }

    /**
     * Gets the banking.Account stored in the banking.AccountNode
     *
     * @return the banking.Account being accessed
     */
    public Account getAccount() {
        return this.Account;
    }

    /**
     * Replaces the banking.Account stored in the banking.AccountNode with the banking.Account provided
     *
     * @param account that will replace the account that is already in the banking.AccountNode
     */
    public void setAccount(Account account) {
        this.Account = account;
    }

    /**
     * Gets the banking.AccountNode that is being pointed to
     *
     * @return the banking.AccountNode being accessed
     */
    public AccountNode getNext() {
        return this.next;
    }

    /**
     * Replaces the banking.AccountNode that is being pointed to with the one provided
     *
     * @param next represents the banking.AccountNode being pointed to
     */
    public void setNext(AccountNode next) {
        this.next = next;
    }

    /**
     * Converts banking.AccountNode to a string that can be printed
     *
     * @return the String representation of the banking.Account as banking.Account Number, banking.Profile of the holder, and balance
     */
    @Override
    public String toString() {
        return Account.toString();
    }
}

/**
 * AccountNode class used for the Archive to store closed Accounts in a linked list implementation
 *
 @author Vishal Saravanan, Yining Chen
 */

public class AccountNode {
    private AccountNode next; //pointer to the next AccountNode
    private Account Account;  //Account object with account number, profile of holder, and balance

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
     * Gets the Account stored in the AccountNode
     *
     * @return the Account being accessed
     */
    public Account getAccount() {
        return this.Account;
    }

    /**
     * Replaces the Account stored in the AccountNode with the Account provided
     *
     * @param account that will replace the account that is already in the AccountNode
     */
    public void setAccount(Account account) {
        this.Account = account;
    }

    /**
     * Gets the AccountNode that is being pointed to
     *
     * @return the AccountNode being accessed
     */
    public AccountNode getNext() {
        return this.next;
    }

    /**
     * Replaces the AccountNode that is being pointed to with the one provided
     *
     * @param next represents the AccountNode being pointed to
     */
    public void setNext(AccountNode next) {
        this.next = next;
    }

    /**
     * Converts AccountNode to a string that can be printed
     *
     * @return the String representation of the Account as Account Number, Profile of the holder, and balance
     */
    @Override
    public String toString() {
        return Account.toString();
    }
}

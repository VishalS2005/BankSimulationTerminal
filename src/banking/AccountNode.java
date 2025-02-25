package banking;

import util.Date;

/**
 * AccountNode class used for the Archive to store closed Accounts in a linked list implementation.
 *
 @author Vishal Saravanan, Yining Chen
 */

public class AccountNode {
    /**
     * pointer to the next AccountNode in the archive
     */
    private AccountNode next;

    /**
     * Account object with account number, profile of holder, and balance
     */
    private final Account Account;

    private Date close;

    /**
     * Creates an AccountNode object.
     *
     * @param account represents an Account that was closed
     */
    public AccountNode(Account account) {
        this.Account = account;
        this.next = null;
    }

    /**
     * Gets the AccountNode that is being pointed to.
     *
     * @return the AccountNode being accessed
     */
    public AccountNode getNext() {
        return this.next;
    }

    /**
     * Replaces the AccountNode that is being pointed to with the one provided.
     *
     * @param next represents the AccountNode being pointed to
     */
    public void setNext(AccountNode next) {
        this.next = next;
    }

    /**
     * Converts AccountNode to a string that can be printed.
     *
     * @return the String representation of the Account as Account Number, Profile of the holder, and balance
     */
    @Override
    public String toString() {
        return Account.toString();
    }
}

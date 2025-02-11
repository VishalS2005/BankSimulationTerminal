package banking;

/**
 * Archive will hold Accounts that have been closed
 *
 * @author Vishal Saravanan, Yining Chen
 */

public class Archive {
    private AccountNode first; //'first' represents the head node of the linked list of Account objects

    /**
     * Constructor for a new Archive that holds all closed Accounts
     */
    public Archive() {
        this.first = null;
    }

    /**
     * Adds a AccountNode object to the front of the linked list instance variable, first
     *
     * @param account object that will be used to create a AccountNode and will be added to the front of the linked list instance variable, first
     */
    public void add(Account account) {
        AccountNode newNode = new AccountNode(account);
        if (first != null) {
            newNode.setNext(first);
        }
        first = newNode;
    }

    /**
     * Traverses through the linked list, first, and prints each account
     * Every Account Number, Holder, and Balance of the Account are printed from the Archive
     */
    public void print() {
        System.out.println("\n*List of closed accounts in the archive.");
        AccountNode current = first; //current is the variable used to iterate through the linked list, first
        while(current != null) {
            System.out.println(current);
            current = current.getNext();
        }
        System.out.println("*end of list.\n");
    }
}

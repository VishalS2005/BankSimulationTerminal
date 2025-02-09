package banking;

/**
 * banking.Archive will hold Accounts that have been closed
 *
 * @author Vishal Saravanan, Yining Chen
 */

public class Archive {
    private AccountNode first; //'first' represents the head node of the linked list of banking.Account objects

    public Archive() { //Initializes an empty banking.Archive with no banking.Account objects.
        first = null;
    }

    /**
     * Adds a banking.AccountNode object to the front of the linked list instance variable, first
     *
     * @param account object that will be used to create a banking.AccountNode and will be added to the front of the linked list instance variable, first
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
     * Every banking.Account Number, Holder, and Balance of the banking.Account are printed from the banking.Archive
     */
    public void print() {
        AccountNode current = first; //current is the variable used to iterate through the linked list, first
        while(current != null) {
            System.out.println(current);
            current = current.getNext();
        }
    }

}

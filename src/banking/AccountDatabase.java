package banking;

/**
 * The banking.AccountDatabase class holds the information regarding all the bank accounts
 * A new account is always added to the end of the array. An instance of this class is a growable list
 * with an initial array capacity of 4, and it automatically increases the capacity by 4 whenever it is full.
 * The list does not decrease in capacity.
 *
 * @author Vishal Saravanan, Yining Chen
 */

public class AccountDatabase {
    private Account [] accounts; //array-based implementation of a linear data structure to hold the list of account objects
    private int size; //represents the amount of accounts
    private final Archive archive; //a linked list of closed account

    public AccountDatabase() { //constructor
        this.accounts = new Account[4];
        this.size = 0;
        this.archive = new Archive();
    }

    /**
     * Searches for an account in the account database
     *
     * @param account that is being searched for
     * @return index of the account in the account database when found,
     * -1 otherwise
     */
    private int find(Account account) {
        for(int i = 0; i < this.size; i++) {
            if(this.accounts[i].equals(account)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Resizes the original database by increasing the size of the array that holds the banking.Account objects by 4
     * Creates a temporary array with the new length and replaces the old array
     */
    private void grow() {
        int newLength = this.accounts.length + 4; //the new length can hold 4 more accounts than the old length
        Account[] newAccounts = new Account[newLength];
        for(int i = 0; i < this.size; i++) {
            newAccounts[i] = this.accounts[i];
        }
        this.accounts = newAccounts;
        this.size = newLength; //updates size of banking.AccountDatabase
    }

    /**
     * Iterates through the banking.AccountDatabase to check for an account
     *
     * @param account that is being searched for in the banking.AccountDatabase
     * @return true if the account is found in the database,
     * false otherwise
     */
    public boolean contains(Account account) {
        for(int i = 0; i < this.size; i++) {
            if(this.accounts[i].equals(account)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a banking.Account to the banking.AccountDatabase
     * Checks for duplicate accounts and returns if there is one found
     * Checks size and resizes as needed to make space for the new banking.Account
     *
     * @param account that is being added to banking.AccountDatabase
     */
    public void add(Account account) {
        if(this.contains(account)) {
            return;
        }
        if(size == accounts.length) {
            grow();
        }

        accounts[size] = account;
        size++;
    }

    /**
     * Removes a banking.Account to the banking.AccountDatabase
     * Checks if the account to be removed is in the banking.AccountDatabase and returns if it is not
     * Adds removed banking.Account to the archive before deleting the banking.Account
     * Replaces banking.Account being deleted with the last banking.Account
     * Updates the size of the banking.AccountDatabase
     *
     * @param account that is being added to banking.AccountDatabase
     */
    public void remove(Account account) {
        int index = find(account); //represents index of account
        if(index == -1) {
            return;
        }
        archive.add(accounts[index]);
        accounts[index] = accounts[size - 1];
        accounts[size - 1] = null;
        size--;

    }

    /**
     * Checks if money can be withdrawn from an account
     *
     * @param number banking.AccountNumber that identifies the account
     * @param amount value of money that will be withdrawn
     * @return true if the amount can be withdrawn
     * false otherwise
     */
    public boolean withdraw(AccountNumber number, double amount) {
        return false; // implement later
    }

    /**
     * Deposits money into a banking.Account which will increase the account balance
     * Searches through the banking.AccountDatabase for the banking.Account before depositing the amount of money into that banking.Account
     *
     * @param number banking.AccountNumber that identifies the account
     * @param amount value of money that will be deposited
     */
    public void deposit(AccountNumber number, double amount) {
        for (int i = 0; i < size; i++) {
            if (this.accounts[i].getAccountNumber().equals(number)) {
                this.accounts[i].deposit(amount);
                return;
            }
        }
    }

    /**
     * Prints all the Accounts that have been closed and are in the banking.Archive
     */
    public void printArchive() {
        this.archive.print();
    }//print closed accounts


    public void printByBranch() {
        //implement later
    }
    public void printByHolder() {
        //implement later
    }
    public void printByType() {
        //implement later
    }

    /**
     * Prints all the Accounts in the banking.AccountDatabase
     */
    public void print() {
        for(int i = 0; i < this.size; i++) {
            System.out.println(this.accounts[i].toString());
        }
    }
}

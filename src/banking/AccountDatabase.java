package banking;

/**
 * The AccountDatabase class holds the information regarding all the bank accounts
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

    private static final int NOT_FOUND = -1;
    private static final int GROW_SIZE = 4;


    public AccountDatabase() { //constructor
        this.accounts = new Account[4];
        this.size = 0;
        this.archive = new Archive();
    }

    /**
     * Searches for an Account in the AccountDatabase using Account
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
        return NOT_FOUND;
    }

    /**
     * Searches for an Account in the AccountDatabase using AccountNumber
     *
     * @param accountNumber that is being searched for
     * @return index of the Account in the AccountDatabase when found,
     * -1 otherwise
     */
    public int find(AccountNumber accountNumber) {
        for(int i = 0; i < this.size; i++) {
            if(this.accounts[i].getAccountNumber().equals(accountNumber)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Resizes the original database by increasing the size of the array that holds the Account objects by 4
     * Creates a temporary array with the new length and replaces the old array
     */
    private void grow() {
        int newLength = this.accounts.length + GROW_SIZE; //the new length can hold 4 more accounts than the old length
        Account[] newAccounts = new Account[newLength];
        for(int i = 0; i < this.size; i++) {
            newAccounts[i] = this.accounts[i];
        }
        this.accounts = newAccounts;
    }

    /**
     * Searches through the AccountDatabase for an Account
     *
     * @param account that is being searched for
     * @return true if Account is found in AccountDatabase
     * false otherwise
     */
    public boolean contains(Account account) {
        return find(account) != NOT_FOUND;
    }

    /**
     * Searches through the AccountDatabase for an Account using accountNumber
     *
     * @param accountNumber that is being searched for
     * @return true if Account is found in AccountDatabase
     * false otherwise
     */
    public boolean contains(AccountNumber accountNumber) {
        for(int i = 0; i < this.size; i++) {
            if(this.accounts[i].getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches through the AccountDatabase for an Account based on first name, last name, dateOfBirth, and account type
     *
     * @param firstName String that is being searched for, comparison is case-insensitive
     * @param lastName String that is being searched for, comparison is case-insensitive
     * @param dateOfBirth object that is being searched for
     * @param type AccountType object that is being searched for
     * @return true if Account is found in AccountDatabase
     * false otherwise
     */
    public boolean contains(String firstName, String lastName, Date dateOfBirth, AccountType type) {
        for(int i = 0; i < this.size; i++) {
            if(this.accounts[i].getFirstName().equalsIgnoreCase(firstName)
               && this.accounts[i].getLastName().equalsIgnoreCase(lastName)
               && this.accounts[i].getAccountNumber().getType().equals(type)
               && this.accounts[i].getDateOfBirth().equals(dateOfBirth)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches through the AccountDatabase for an Account based on first name, last name, and dateOfBirth
     *
     * @param firstName String that is being searched for
     * @param lastName String that is being searched for
     * @param dateOfBirth object that is being searched for
     * @return true if Account is found in AccountDatabase
     * false otherwise
     */
    public boolean contains(String firstName, String lastName, Date dateOfBirth) {
        for(int i = 0; i < this.size; i++) {
            if(this.accounts[i].getFirstName().equalsIgnoreCase(firstName) &&
                    this.accounts[i].getLastName().equalsIgnoreCase(lastName) &&
                    this.accounts[i].getDateOfBirth().equals(dateOfBirth)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a Account to the AccountDatabase
     * Checks for duplicate accounts and returns if there is one found
     * Checks size and resizes as needed to make space for the new Account
     *
     * @param account being added to AccountDatabase
     */
    public void add(Account account) {
        if(this.contains(account)) {
            return;
        }
        if(this.size == this.accounts.length - 1) {
            grow();
        }

        accounts[size] = account;
        size++;
    }

    /**
     * Removes a Account to the AccountDatabase
     * Checks if the account to be removed is in the AccountDatabase and returns if it is not
     * Adds removed Account to the archive before deleting the Account
     * Replaces Account being deleted with the last Account
     * Updates the size of the AccountDatabase
     *
     * @param account that is being added to AccountDatabase
     */
    public void remove(Account account) {
        int index = find(account.getAccountNumber()); //represents index of account
        if(index == -1) {
            return;
        }
        accounts[index].emptyBalance();
        archive.add(accounts[index]);
        accounts[index] = accounts[size - 1];
        accounts[size - 1] = null;
        size--;
    }

    /**
     * Removes a Account to the AccountDatabase
     * Checks if the account to be removed is in the AccountDatabase and returns if it is not
     * Adds removed Account to the archive before deleting the Account
     * Replaces Account being deleted with the last Account
     * Updates the size of the AccountDatabase
     *
     * @param firstName of the Account
     * @param lastName of the Account
     * @param dateOfBirth of the Account
     */
    public void remove (String firstName, String lastName, Date dateOfBirth) {
        for(int i = 0; i < this.size; i++) {
            if(this.accounts[i].getFirstName().equalsIgnoreCase(firstName)
               && this.accounts[i].getLastName().equalsIgnoreCase(lastName)
               && this.accounts[i].getDateOfBirth().equals(dateOfBirth)) {
                  this.remove(accounts[i]);
                  i--; //check the last element since when removing, last element is switched with current
            }
        }
    }

    /**
     * Checks if money can be withdrawn from an account
     *
     * @param number AccountNumber that identifies the account
     * @param amount value of money that will be withdrawn
     * @return true if the amount can be withdrawn
     * false otherwise
     */
    public boolean withdraw(AccountNumber number, double amount) {
        int index = find(number);
        if(index == -1) {
            return false;
        }
        accounts[index].withdraw(amount);
        if(accounts[index].getBalance() < 2000 && accounts[index].getType() == AccountType.MONEY_MARKET) {
            accounts[index].setAccountType(AccountType.SAVINGS);
        }
        return true;
    }

    public boolean hasSufficientFunds(int index, double amount) {
        return accounts[index].getBalance() >= amount;
    }

    public boolean willDowngrade(int index, double amount) {
        return accounts[index].getBalance() - amount < 2000 && accounts[index].getType() == AccountType.MONEY_MARKET;
    }

    /**
     * Deposits money into a Account which will increase the account balance
     * Searches through the AccountDatabase for the Account before depositing the amount of money into that Account
     *
     * @param number AccountNumber that identifies the account
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
     * Prints all the Accounts that have been closed and are in the Archive
     */
    public void printArchive() {
        this.archive.print();
    }//print closed accounts




    public void printByBranch() {
        // Bubble sort: iterate through the array and swap adjacent elements if they are out of order.
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if ((accounts[j].compareByBranch(accounts[j + 1])) > 0) {
                    Account temp = accounts[j];
                    accounts[j] = accounts[j + 1];
                    accounts[j + 1] = temp;
                }
            }
        }

        String currentCounty = null;

        for (int i = 0; i < this.size; i++) {
            Account account = this.accounts[i];
            String county = account.getAccountNumber().getBranch().getCounty();

            // Print county header when encountering a new county
            if (currentCounty == null || !currentCounty.equals(county)) {
                System.out.println("County: " + county);
                currentCounty = county;
            }

            System.out.println(account);
        }

        System.out.println("*end of list.\n");



    }

    public void printByHolder() {
        // Bubble sort: iterate through the array and swap adjacent elements if they are out of order.
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if ((accounts[j].compareTo(accounts[j + 1])) > 0) {
                    Account temp = accounts[j];
                    accounts[j] = accounts[j + 1];
                    accounts[j + 1] = temp;
                }
            }
        }

        print();
    }




    public void printByType() {
        // Bubble sort by account type, then account number
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                int typeComparison = accounts[j].compareByAccountType(accounts[j + 1]);
                if (typeComparison > 0 || (typeComparison == 0 && accounts[j].getAccountNumber().compareTo(accounts[j + 1].getAccountNumber()) > 0)) {
                    Account temp = accounts[j];
                    accounts[j] = accounts[j + 1];
                    accounts[j + 1] = temp;
                }
            }
        }
        AccountType currentType = null;

        for (int i = 0; i < this.size; i++) {
            Account account = this.accounts[i];
            AccountType accountType = account.getAccountNumber().getType();

            // Print type header when encountering a new type
            if (currentType == null || !currentType.equals(accountType)) {
                System.out.println("Account Type: " + accountType);
                currentType = accountType;
            }

            System.out.println(account);
        }

        System.out.println("*end of list.\n");
    }


    /**
     * Prints all the Accounts in the AccountDatabase
     */
    public void print() {
        for(int i = 0; i < this.size; i++) {
            System.out.println(this.accounts[i].toString());
        }
        System.out.println("*end of list.\n");
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

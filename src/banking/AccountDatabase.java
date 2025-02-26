package banking;

import util.Date;
import util.List;
import util.Sort;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The AccountDatabase class holds the information regarding all the bank accounts.
 * A new account is always added to the end of the array. An instance of this class is a growable list
 * with an initial array capacity of 4, and it automatically increases the capacity by 4 whenever it is full.
 * The list does not decrease in capacity.
 *
 * @author Vishal Saravanan, Yining Chen
 */

public class AccountDatabase extends List<Account> {

    private final Archive archive;


    public AccountDatabase() {
        super();
        this.archive = new Archive();
    }

    /**
     * Deposits money into an Account which will increase the Account's balance.
     * Searches through the AccountDatabase for the Account before depositing the amount of money into that Account.
     * Does nothing if Account is not found using the number.
     *
     * @param number AccountNumber that identifies the account
     * @param amount value of money that will be deposited
     */
    public void deposit(AccountNumber number, double amount) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getAccountNumber().equals(number)) {
                this.get(i).deposit(amount);
                return;
            }
        }
    }

    /**
     * Checks if money can be withdrawn from an account.
     * If the AccountType is Money Market and the balance falls below 2000, changes the AccountType to a savings account.
     *
     * @param number AccountNumber that identifies the Account
     * @param amount value of money that will be withdrawn
     * @return true if the amount can be withdrawn
     * false otherwise
     */
    public boolean withdraw(AccountNumber number, double amount) {
        int index = find(number);
        if(index == -1) {
            return false;
        }
        this.get(index).withdraw(amount);
        if(this.get(index).getBalance() < 2000 && this.get(index).getType() == AccountType.MONEY_MARKET) {
            this.get(index).setAccountType(AccountType.SAVINGS);
        }
        return true;
    }

    /**
     * Checks if the Account has at least a certain amount of money.
     *
     * @param index of AccountDatabase that represents an Account being checked
     * @param amount value of money will be checked
     * @return true if the amount of money in the account is greater than or equal to how much the user is checking for
     * false otherwise
     */
    public boolean hasSufficientFunds(int index, double amount) {
        return this.get(index).getBalance() >= amount;
    }

    /**
     * Checks if the account will downgrade from a Money Market to a savings account after a withdrawal.
     * If the balance of a Money Market account falls below 2000, it will be downgraded.
     *
     * @param index of AccountDatabase that represents an Account being checked
     * @param amount value of money that will be withdrawn
     * @return true if the amount can be withdrawn
     * false otherwise
     */
    public boolean willDowngrade(int index, double amount) {
        return this.get(index).getBalance() - amount < 2000 && this.get(index).getType() == AccountType.MONEY_MARKET;
    }

    /**
     * Adds an Account to the AccountDatabase.
     * Checks for duplicate accounts and returns if there is one found.
     * Checks size and resizes as needed to make space for the new Account.
     *
     * @param account being added to AccountDatabase
     */


    /**
     * Removes an Account from the AccountDatabase.
     * Checks if the account to be removed is in the AccountDatabase and returns if it is not.
     * Adds removed Account to the archive before deleting the Account.
     * Replaces Account being deleted with the last Account.
     * Updates the size of the AccountDatabase.
     *
     * @param account that is being added to AccountDatabase
     */


    /**
     * Removes an Account to the AccountDatabase.
     * Checks if the account to be removed is in the AccountDatabase
     * by checking first name, last name, and date of birth.
     * Calls the remove method that:
     *      * Adds removed Account to the archive before deleting the Account
     *      * Replaces Account being deleted with the last Account
     *      * Updates the size of the AccountDatabase
     *
     * @param firstName of the Account
     * @param lastName of the Account
     * @param dateOfBirth of the Account
     */
    public void remove (String firstName, String lastName, Date dateOfBirth) {
        for(int i = 0; i < this.size(); i++) {
            if (this.get(i).getFirstName().equalsIgnoreCase(firstName)
                    && this.get(i).getLastName().equalsIgnoreCase(lastName)
                    && this.get(i).getDateOfBirth().equals(dateOfBirth)) {
                this.remove(this.get(i));
                i--; //check the last element since when removing, last element is switched with current
            }
        }
    }

    /**
     * Checks if Account is contained in the AccountDatabase and returns index of location in database.
     * Searches for an Account in the AccountDatabase using AccountNumber.
     *
     * @param accountNumber that is being searched for
     * @return index of the Account in the AccountDatabase when found,
     * -1 otherwise
     */
    public int find(AccountNumber accountNumber) {
        for(int i = 0; i < this.size(); i++) {
            if(this.get(i).getAccountNumber().equals(accountNumber)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Checks if Account is contained in the AccountDatabase.
     * Searches through the AccountDatabase for an Account using accountNumber.
     *
     * @param accountNumber that is being searched for
     * @return true if Account is found in AccountDatabase
     * false otherwise
     */
    public boolean contains(AccountNumber accountNumber) {
        for(int i = 0; i < this.size(); i++) {
            if(this.get(i).getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if Account is contained in the AccountDatabase.
     * Searches through the AccountDatabase for an Account based on first name,
     * last name, dateOfBirth, and account type.
     *
     * @param firstName String that is being searched for, comparison is case-insensitive
     * @param lastName String that is being searched for, comparison is case-insensitive
     * @param dateOfBirth object that is being searched for
     * @param type AccountType object that is being searched for
     * @return true if Account is found in AccountDatabase
     * false otherwise
     */
    public boolean contains(String firstName, String lastName, Date dateOfBirth, AccountType type) {
        for(int i = 0; i < this.size(); i++) {
            if(this.get(i).getFirstName().equalsIgnoreCase(firstName)
               && this.get(i).getLastName().equalsIgnoreCase(lastName)
               && this.get(i).getAccountNumber().getType().equals(type)
               && this.get(i).getDateOfBirth().equals(dateOfBirth)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if Account is contained in the AccountDatabase.
     * Searches through the AccountDatabase for an Account based on first name, last name, and dateOfBirth.
     *
     * @param firstName String that is being searched for
     * @param lastName String that is being searched for
     * @param dateOfBirth object that is being searched for
     * @return true if Account is found in AccountDatabase
     * false otherwise
     */
    public boolean contains(String firstName, String lastName, Date dateOfBirth) {
        for(int i = 0; i < this.size(); i++) {
            if(this.get(i).getFirstName().equalsIgnoreCase(firstName) &&
                    this.get(i).getLastName().equalsIgnoreCase(lastName) &&
                    this.get(i).getDateOfBirth().equals(dateOfBirth)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints all the Accounts in the AccountDatabase from the beginning of AccountDatabase to the end.
     */
    public void print() {
        for(int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i).toString());
        }
        System.out.println("*end of list.\n");
    }

    /**
     * Orders and prints AccountDatabase by the 2-digit String, Branch.
     * Bubble Sort implementation is used to iterate through the array
     * and swap adjacent elements if they are out of order.
     * To print, iterate through AccountDatabase and print County followed by City.
     */
    public void printByBranch() {
        Sort.account(this, 'B');
        String currentCounty = null;
        for (int i = 0; i < this.size(); i++) {
            Account account = this.get(i);
            String county = account.getAccountNumber().getBranch().getCounty();
            if (currentCounty == null || !currentCounty.equals(county)) { // Print county header when encountering a new county
                System.out.println("County: " + county);
                currentCounty = county;
            }
            System.out.println(account);
        }
        System.out.println("*end of list.\n");
    }

    /**
     * Orders and prints AccountDatabase by the name and date of birth of the account.
     * Bubble Sort implementation is used to iterate through the array
     * and swap adjacent elements if they are out of order.
     * Calls the print() method.
     */
    public void printByHolder() {
        Sort.account(this, 'H');
        this.print();
    }

    /**
     * Orders and prints AccountDatabase by the AccountType.
     * Bubble Sort implementation is used to iterate through the array
     * and swap adjacent elements if they are out of order.
     * The compareTo method referenced below compares by AccountNumber.
     * Prints sorted by AccountType.
     */
    public void printByType() {
        Sort.account(this, 'T');

        AccountType currentType = null;
        for (int i = 0; i < this.size(); i++) {
            Account account = this.get(i);
            AccountType accountType = account.getAccountNumber().getType();
            if (currentType == null || !currentType.equals(accountType)) { // Print type header when encountering a new type
                System.out.println("Account Type: " + accountType);
                currentType = accountType;
            }
            System.out.println(account);
        }
        System.out.println("*end of list.\n");
    }

    /**
     * Prints all the Accounts that have been closed and are in the Archive
     */
    public void printArchive() {
        this.archive.print();
    }

    public void printStatements() {

    } //print account statements


    public void loadAccounts(File file) throws IOException {
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");
            Account account = TransactionManager.createAccount(parts);
            this.add(account);
        }

    }


    public void processActivities(File file) throws IOException {

        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");
            Date date = TransactionManager.createDate(parts[2]);
            Branch branch = TransactionManager.createBranch(parts[3]);
            char type = parts[0].charAt(0);
            double amount = Double.parseDouble(parts[4]);
            Activity activity = new Activity(date, branch, type, amount, false);
            int index = find(new AccountNumber(parts[1]));
            if(index == -1) {
                continue;
            }
            this.get(index).addActivity(activity);
        }

    }

}

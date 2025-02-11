package banking;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Transaction Manager class that reads in transactions from the command line
 * Depending on the action, completes transaction or does not execute it
 *
 @author Vishal Saravanan, Yining Chen
 */

public class TransactionManager {
    private static final AccountDatabase accountDatabase = new AccountDatabase(); //holds all of the Accounts

    private static DecimalFormat df = new DecimalFormat("#,##0.00");

    /**
     * Gets the AccountType from a provided String representation of the three types of Accounts
     * Checking, Savings, Money Market
     *
     * @param type String representation of the type of Account
     * @return AccountType of the Account
     */
    private static AccountType getAccountType(String type) {
        AccountType acctType = null;
        String typeToken = type.toLowerCase();
        switch (typeToken) {
            case "checking":
                acctType = AccountType.CHECKING;
                break;
            case "savings":
                acctType = AccountType.SAVINGS;
                break;
            case "moneymarket":
                acctType = AccountType.MONEY_MARKET;
                break;
            default:  {
                System.out.println(typeToken + " - invalid account type.");
            }
        }
        return acctType;
    }

    /**
     * Gets the Branch from a provided String representation of the branch
     * Throws exception if the Branch name provided is invalid
     *
     * @param branchName String representation of the Branch where the Account was opened
     * @return Branch of the Account
     */
    private static Branch getBranch(String branchName) {
        Branch branch = null;
        try {
            branch = Branch.valueOf(branchName.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(branchName + " - invalid branch.");
        }
        return branch;
    }

    /**
     * Gets the Account based on the parameters
     *
     * @param firstName String representation of first name of Account holder
     * @param lastName String representation of last name of Account holder
     * @param dateOfBirth Date object that represents date of birth of Account holder
     * @param branch Branch object that represents which branch the account is at
     * @param acctType AccountType object that represents what kind of Account it is
     * @return Account that is in the database
     */
    private static Account getAccount(String firstName, String lastName, Date dateOfBirth, Branch branch, AccountType acctType) {
        Profile holder = getProfile(firstName, lastName, dateOfBirth);
        return new Account(branch, acctType, holder);
    }

    /**
     * Gets the Profile based on first name, last name, and dateOfBirth
     * Creates a new Profile object
     *
     * @param firstName String representation of first name of Account holder
     * @param lastName String representation of last name of Account holder
     * @param dateOfBirth Date object that represents date of birth of Account holder
     * @return Profile for the Account
     */
    private static Profile getProfile(String firstName, String lastName, Date dateOfBirth) {
        return new Profile(firstName, lastName, dateOfBirth);
    }

    /**
     * Gets the date of birth based on String representation of the date
     * Date of birth formatted as such: ##/##/####
     *
     * @param date String representation of the date
     * @return Date object that represents the date of birth
     */
    private static Date getDate(String date) {
        String[] dateParts = date.split("/");
        int month = Integer.parseInt(dateParts[0]);
        int day = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        return new Date(year, month, day);
    }

    /**
     * Initiates reading of inputs from the command line
     * Ceases reading of inputs once a "Q" is read
     * Example of a single line of input:
     * The user enters:
     *    O <accountType> <branch> <firstName> <lastName> <dob> <initialDeposit>
     * For example:
     *    O savings bridgewater John Doe 2/19/2000 500
     */
    public static void run() {
        System.out.println("Transaction Manager is running.");
        Scanner scanner = new Scanner(System.in);
        while (true) { // loop only ends when a "Q" is read
            String command = scanner.nextLine();
            String[] commandArray = command.split("\\s+");
            if (command.trim().isEmpty()) {
                continue;
            }
            if(commandArray[0].equals("Q")) {
                System.out.println("Transaction Manager is terminated.");
                return;
            }
            processCommand(commandArray);
        }
    }

    /**
     * Chooses which action to complete depending on a single input line that has been read
     * VALID COMMANDS: O, C, D, W, P, PA, PB, PH, PT
     * otherwise will identify that the command was invalid
     * O --> opens a new Account, if not already there
     * C --> closes an Account, if in the database
     * D --> deposits money into an Account, if in the database
     * W --> withdraws money from an Account, if in the database and money available
     * P --> prints the AccountDatabase
     * PA --> prints the Archive
     * PB --> prints ordered by Branch (county, then city)
     * PH --> prints by holder then AccountNumber
     * PT --> prints by AccountType then AccountNumber
     *
     * @param commandArray Holds the input that has been extracted and put into a String array
     */
    private static void processCommand(String[] commandArray) {
        switch (commandArray[0]) {
            case "O":
                openAccount(commandArray);
                break;
            case "C":
                closeAccount(commandArray);
                break;
            case "D":
                depositMoney(commandArray);
                break;
            case "W":
                withdrawMoney(commandArray);
                break;
            case "P":
                if(accountDatabase.isEmpty()) {
                    System.out.println("Account database is empty!");
                }
                else {
                    System.out.println("\n*List of accounts in the account database.");
                    accountDatabase.print();
                }
                break;
            case "PA":
                accountDatabase.printArchive();
                break;
            case "PB":
                if(accountDatabase.isEmpty()) {
                    System.out.println("Account database is empty!");
                }
                else {
                    System.out.println("\n*List of accounts ordered by branch location (county, city).");
                    accountDatabase.printByBranch();
                }
                break;
            case "PH":
                if(accountDatabase.isEmpty()) {
                    System.out.println("Account database is empty!");
                }
                else {
                    System.out.println("\n*List of accounts ordered by account holder and number.");
                    accountDatabase.printByHolder();
                }
                break;
            case "PT":
                if(accountDatabase.isEmpty()) {
                    System.out.println("Account database is empty!");
                }
                else {
                    System.out.println("\n*List of accounts ordered by account type and number.");
                    accountDatabase.printByType();
                }
                break;
            default:
                System.out.println("Invalid command!");
        }
    }

    /**
     * Executed to open a new Account when the first command is "O"
     * Checks for valid inputs including a valid AccountType, Branch, Date, and balance
     * Checks for duplicate account, minimum balance, and money market specifications
     * Adds the opened account to the database
     *
     * @param commandArray Holds the input that has been extracted and put into a String array
     */
    private static void openAccount(String[] commandArray) {
        AccountType acctType = getAccountType(commandArray[1]); //first input is the AccountType
        if(acctType == null) {return;}
        Branch branch = getBranch(commandArray[2]); //second input is the Branch
        if(branch == null) { return; }
        String dateOfBirth = commandArray[5]; //fifth input is the date of birth
        Date dob = getDate(dateOfBirth);
        if (!dob.isValid()) {
            System.out.println("DOB invalid: " + dob + " not a valid calendar date!");
            return;
        } else if (dob.isAfterToday()) {
            System.out.println("DOB invalid: " + dob + " cannot be today or a future day.");
            return;
        } else if(!dob.isEighteen()) {
            System.out.println("Not eligible to open: " +  dateOfBirth + " under 18.");
            return;
        }
        String firstName = commandArray[3]; //third input is the first name of the holder
        String lastName = commandArray[4]; //fourth input is the last name of the holder
        double balance;
        try {
            balance = Double.parseDouble(commandArray[6]); //sixth input is the balance of the holder when opening
        } catch (NumberFormatException e) {
            System.out.println("For input string: \"" + commandArray[6] + "\" - not a valid amount.");
            return;
        }
        if(accountDatabase.contains(firstName, lastName, dob, acctType)) { //checking for a duplicate account
            System.out.println(firstName + " " + lastName +   " already has a " + commandArray[1] + " account.");
            return;
        }
        if(balance <= 0) { //balance must be more than 0
            System.out.println("Initial deposit cannot be 0 or negative.");
            return;
        } else if(balance < 2000 && acctType.equals(AccountType.MONEY_MARKET)) { //Money Market account must have at least $2000
            System.out.println("Minimum of $2,000 to open a Money Market account.");
            return;
        }
        Account account = getAccount(firstName, lastName, dob, branch, acctType);
        account.deposit(balance);
        accountDatabase.add(account); //adds the Account to the database
        System.out.println(account.getAccountNumber().getType() +  " account " + account.getAccountNumber() + " has been opened.");
    }

    /**
     * Executed to close an exiting Account when the first command is "C"
     * Closes an Account by removing it from the AccountDatabase and adding it to the Archive
     * Accounts for two different types of formatting in closing an Account:
     * C <accountNumber>
     * or C <firstName> <lastName> <dateOfBirth>
     *
     * @param commandArray Holds the input that has been extracted and put into a String array
     */
    private static void closeAccount(String[] commandArray) {
        if(commandArray.length == 2) {
            AccountNumber accountNumber = new AccountNumber(commandArray[1]);
            if(!accountDatabase.contains(accountNumber)) {
                System.out.println(accountNumber + " account does not exist.");
                return;
            }
            accountDatabase.remove(new Account(accountNumber));
            System.out.println(accountNumber +  " is closed and moved to archive; balance set to 0.");
        } else {
            String firstName = commandArray[1];
            String lastName = commandArray[2];
            Date dateOfBirth = getDate(commandArray[3]);
            if(!accountDatabase.contains(firstName, lastName, dateOfBirth)) {
                System.out.println(firstName + " " + lastName + " " + dateOfBirth +  " does not have any accounts in the database.");
                return;
            }
            accountDatabase.remove(firstName, lastName, dateOfBirth);
            System.out.println("All accounts for " + firstName + " " + lastName + " " + dateOfBirth + " are closed and moved to archive; balance set to 0.");
        }
    }

    /**
     * Executed to deposit money to an existing Account when the first command is "D"
     * Checks for a deposit amount less than or equal to 0
     * Formatting of input:
     * D <accountNumber> <depositAmount>
     *
     * @param commandArray Holds the input that has been extracted and put into a String array
     */
    private static void depositMoney(String[] commandArray) {
        try {
            double depositAmount = Double.parseDouble(commandArray[2]);
            if(depositAmount <= 0) {
                System.out.println(depositAmount + " - deposit amount cannot be 0 or negative.");
                return;
            }
            AccountNumber accountNumber = new AccountNumber(commandArray[1]);
            accountDatabase.deposit(accountNumber, depositAmount);
            System.out.println("$" + df.format(depositAmount) +  " deposited to " + accountNumber);
        } catch (NumberFormatException e) {
            System.out.println("For input string: \"" + commandArray[2] + "\" - not a valid amount.");
        }
    }

    /**
     * Executed to deposit money to an existing Account when the first command is "W"
     * Checks for a withdrawal amount less than or equal to 0
     * Additionally checks if the Account has sufficient funds or needs to be downgraded from
     * a money market account to a savings account
     * Formatting of input:
     * W <accountNumber>  <withdrawalAmount>
     *
     * @param commandArray Holds the input that has been extracted and put into a String array
     */
    private static void withdrawMoney(String[] commandArray) {
        try {
            double withdrawalAmount = Double.parseDouble(commandArray[2]);
            if(withdrawalAmount <= 0) {
                System.out.println(withdrawalAmount + " withdrawal amount cannot be 0 or negative.");
                return;
            }
            AccountNumber accountNumber = new AccountNumber(commandArray[1]);

            int index = accountDatabase.find(accountNumber);
            if(index == -1) {
                System.out.println(accountNumber + " does not exist.");
                return;
            }
            if(!accountDatabase.hasSufficientFunds(index, withdrawalAmount)) {
                System.out.println(accountNumber + " - insufficient funds.");
                return;
            }
            if(accountDatabase.willDowngrade(index, withdrawalAmount)) {
                System.out.print(accountNumber + " is downgraded to SAVINGS - ");
            }
            System.out.println("$" + df.format(withdrawalAmount) +  " withdrawn from " + accountNumber);
            accountDatabase.withdraw(accountNumber, withdrawalAmount);
        } catch (NumberFormatException e) {
            System.out.println("For input string: \"" + commandArray[2] + "\" - not a valid amount.");
        }
    }
}

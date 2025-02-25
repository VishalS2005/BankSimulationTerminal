package banking;

import util.Date;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Transaction Manager class that reads in transactions from the command line.
 * Depending on the action, completes transaction or does not execute it.
 *
 @author Vishal Saravanan, Yining Chen
 */

public class TransactionManager {
    /**
     * holds all the accounts
     */
    private static final AccountDatabase accountDatabase = new AccountDatabase();

    /**
     * formats values of money
     */
    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    /**
     * Creates an AccountType object using a provided String representation of the three types of Accounts.
     * Types of accounts: Checking(01), Savings(02), Money Market(03).
     *
     * @param type String representation of the type of Account
     * @return AccountType of the Account
     */
    private static AccountType createAccountType(String type) {
        AccountType acctType = null;
        String typeToken = type.toLowerCase(); //AccountType is case-insensitive
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
     * Creates a Branch object from a provided String representation of the branch.
     * Throws exception if the Branch name provided is invalid.
     *
     * @param branchName String representation of the Branch where the Account was opened
     * @return Branch of the Account
     */
    private static Branch createBranch(String branchName) {
        Branch branch = null;
        try {
            branch = Branch.valueOf(branchName.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(branchName + " - invalid branch.");
        }
        return branch;
    }

    /**
     * Creates a Profile object based on first name, last name, and dateOfBirth.
     * Creates a new Profile object.
     *
     * @param firstName String representation of first name of Account holder
     * @param lastName String representation of last name of Account holder
     * @param dateOfBirth Date object that represents date of birth of Account holder
     * @return Profile for the Account
     */
    private static Profile createProfile(String firstName, String lastName, Date dateOfBirth) {
        return new Profile(firstName, lastName, dateOfBirth);
    }

    /**
     * Creates a Date object based on String representation of the date.
     * Date of birth formatted as such: ##/##/####
     *
     * @param date String representation of the date
     * @return Date object that represents the date of birth
     */
    private static Date createDate(String date) {
        String[] dateParts = date.split("/");
        int month = Integer.parseInt(dateParts[0]);
        int day = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        return new Date(month, day, year);
    }


    private static Account createAccount(String[] commandArray, String firstName, String lastName, Date dateOfBirth, Branch branch) {
        Profile holder = createProfile(firstName, lastName, dateOfBirth);
        String accountType = commandArray[1];

        return switch (accountType) {
            case "checking" -> new Checking(branch, AccountType.CHECKING, holder);
            case "savings" -> new Savings(branch, AccountType.SAVINGS, holder);
            case "moneymarket" -> new MoneyMarket(branch, AccountType.MONEY_MARKET, holder);
            case "college" -> new CollegeChecking(branch, AccountType.COLLEGE_CHECKING, holder, Campus.fromCode(commandArray[7]));
            case "certificate" -> new CertificateDeposit(branch, AccountType.CD, holder, Integer.parseInt(commandArray[7]), createDate(commandArray[7]));
            default -> throw new IllegalStateException("Unexpected value: " + accountType);
        };
    }

    /**
     * Chooses which action to complete depending on a single input line that has been read.
     * VALID COMMANDS: O, C, D, W, P, PA, PB, PH, PT
     * Otherwise, will identify that the command was invalid.
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
        if (isValidCommand(commandArray[0])) {
            switch (commandArray[0]) {
                case "O":
                    openAccount(commandArray);
                    return;
                case "C":
                    closeAccount(commandArray);
                    return;
                case "D":
                    depositMoney(commandArray);
                    return;
                case "W":
                    withdrawMoney(commandArray);
                    return;
            }
            if (accountDatabase.isEmpty()) {
                System.out.println("Account database is empty!");
            } else {
                printAccounts(commandArray[0]);
            }
        }
    }

    /**
     * Determines which type of print command is needed and prints the Accounts in a certain order.
     * VALID COMMANDS: P, PA ,PB, PH, PT.
     * P --> prints the AccountDatabase
     * PA --> prints the Archive
     * PB --> prints ordered by Branch (county, then city)
     * PH --> prints by holder then AccountNumber
     * PT --> prints by AccountType then AccountNumber
     *
     * @param command 1-2 letter String command to determine what action to take
     */
    private static void printAccounts(String command) {
        switch (command) {
            case "P":
                System.out.println("P command is deprecated!");
                break;
            case "PA":
                accountDatabase.printArchive();
                break;
            case "PB":
                System.out.println("\n*List of accounts ordered by branch location (county, city).");
                accountDatabase.printByBranch();
                break;
            case "PH":
                System.out.println("\n*List of accounts ordered by account holder and number.");
                accountDatabase.printByHolder();
                break;
            case "PT":
                System.out.println("\n*List of accounts ordered by account type and number.");
                accountDatabase.printByType();
                break;
        }
    }

    /**
     * Determines if the command provided is valid.
     * VALID COMMANDS: O, C, D, W, P, PA, PB, PH, PT.
     * Otherwise, will identify that the command was invalid.
     *
     * @param command 1-2 letter String command to determine what action to take
     * @return true if the command is recognized and can be executed
     * false otherwise
     */
    private static boolean isValidCommand(String command) {
        return switch (command) {
            case "O", "C", "D", "W", "P", "PA", "PB", "PH", "PT" -> true;
            default -> {
                System.out.println("Invalid command!");
                yield false;
            }
        };
    }

    /**
     * Executed to open a new Account when the first command is "O".
     * Checks for valid inputs including a valid AccountType, Branch, Date, and balance.
     * Checks for duplicate account, minimum balance, and money market specifications.
     * Adds the opened account to the database.
     *
     * @param commandArray Holds the input that has been extracted and put into a String array
     */
    private static void openAccount(String[] commandArray) {
        AccountType acctType = createAccountType(commandArray[1]); //first input is the AccountType
        if(acctType == null) {
            return;
        }
        Branch branch = createBranch(commandArray[2]); //second input is the Branch
        if(branch == null) {
            return;
        }

        Date dob = createDate(commandArray[5]); //fifth input is the Date of Birth of the holder
        if (!checkDateOfBirth(dob)) {
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

        if(!checkBalance(balance, acctType)) {
            return;
        }

        Account account = createAccount(commandArray, firstName, lastName, dob, branch);
        account.deposit(balance);
        accountDatabase.add(account); //adds the Account to the database
        System.out.println(account.getAccountNumber().getType() +  " account " + account.getAccountNumber() + " has been opened.");
    }

    /**
     * Checks a Date object to see if it is a valid date.
     * Returns false if not a valid calendar date,
     * if the birthdate provided is after the current date,
     * or if the birthdate provided indicates that the person is 18 years old.
     *
     * @param dob Date object which represents the date of birth
     * @return true if valid date of birth
     * false otherwise
     */
    private static boolean checkDateOfBirth(Date dob) {
        if (!dob.isValid()) {
            System.out.println("DOB invalid: " + dob + " not a valid calendar date!");
            return false;
        } else if (dob.isAfterToday()) {
            System.out.println("DOB invalid: " + dob + " cannot be today or a future day.");
            return false;
        } else if(!dob.isEighteen()) {
            System.out.println("Not eligible to open: " +  dob + " under 18.");
            return false;
        }
        return true;
    }

    /**
     * Checks if balance of the Account being opened is valid.
     * A starting balance of 0 or less is invalid.
     * A Money Market Account with less than 2000 is invalid.
     *
     * @param balance value of money stored in Account being opened
     * @param acctType type of Account being opened
     * @return true if balance is a valid amount
     * false otherwise
     */
    private static boolean checkBalance(double balance, AccountType acctType) {
        if(balance <= 0) { //balance must be more than 0
            System.out.println("Initial deposit cannot be 0 or negative.");
            return false;
        } else if(balance < 2000 && acctType.equals(AccountType.MONEY_MARKET)) { //Money Market account must have at least $2000
            System.out.println("Minimum of $2,000 to open a Money Market account.");
            return false;
        }
        return true;
    }

    /**
     * Executed to close an exiting Account when the first command is "C".
     * Closes an Account by removing it from the AccountDatabase and adding it to the Archive.
     * Accounts for two different types of formatting in closing an Account:
     * C <accountNumber>
     * or C <firstName> <lastName> <dateOfBirth>
     *
     * @param commandArray Holds the input that has been extracted and put into a String array
     */
    private static void closeAccount(String[] commandArray) {
        if(commandArray.length == 2) { //AccountNumber input
            AccountNumber accountNumber = new AccountNumber(commandArray[1]);
            if(!accountDatabase.contains(accountNumber)) {
                System.out.println(accountNumber + " account does not exist.");
                return;
            }
            System.out.println(accountNumber +  " is closed and moved to archive; balance set to 0.");
        } else { //First Name, Last Name, and Date of Birth input
            String firstName = commandArray[1];
            String lastName = commandArray[2];
            Date dateOfBirth = createDate(commandArray[3]);
            if(!accountDatabase.contains(firstName, lastName, dateOfBirth)) {
                System.out.println(firstName + " " + lastName + " " + dateOfBirth +  " does not have any accounts in the database.");
                return;
            }
            accountDatabase.remove(firstName, lastName, dateOfBirth);
            System.out.println("All accounts for " + firstName + " " + lastName + " " + dateOfBirth + " are closed and moved to archive; balance set to 0.");
        }
    }

    /**
     * Executed to deposit money to an existing Account when the first command is "D".
     * Checks for a deposit amount less than or equal to 0.
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
     * Executed to deposit money to an existing Account when the first command is "W".
     * Checks for a withdrawal amount less than or equal to 0.
     * Additionally, checks if the Account has sufficient funds or needs to be downgraded from
     * a money market account to a savings account.
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

    /**
     * Initiates reading of inputs from the command line.
     * Ceases reading of inputs once a "Q" is read.
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
}

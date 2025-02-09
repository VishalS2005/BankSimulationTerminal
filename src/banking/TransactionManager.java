package banking;

import java.util.Scanner;

/**
 * Transaction Manager class that reads in transactions from the command line
 * Depending on the action, completes transaction or does not execute it
 *
 @author Vishal Saravanan, Yining Chen
 */

public class TransactionManager {
    private static final AccountDatabase accountDatabase = new AccountDatabase();

    public static void run() {
        System.out.println("Transaction Manager is running.");
        Scanner scanner = new Scanner(System.in);
        while (true) {
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
                accountDatabase.print();
                break;
            case "PA":
                accountDatabase.printArchive();
                break;
            case "PB":
                accountDatabase.printByBranch();
                break;
            case "PH":
                accountDatabase.printByHolder();
                break;
            case "PT":
                accountDatabase.printByType();
                break;
            default:
                System.out.println("Invalid command!");
        }
    }

    /**
     * The user enters:
     *    O <accountType> <branch> <firstName> <lastName> <dob> <initialDeposit>
     * For example:
     *    O savings bridgewater John Doe 2/19/2000 500
     */
    private static void openAccount(String[] commandArray) {

        AccountType acctType = getAccountType(commandArray[1]);
        if(acctType == null) {
            return;
        }

        Branch branch = getBranch(commandArray[2]);
        if(branch == null) {
            return;
        }

        String dateOfBirth = commandArray[5];
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

        String firstName = commandArray[3];
        String lastName = commandArray[4];




        double balance;
        try {
            balance = Double.parseDouble(commandArray[6]);
        } catch (NumberFormatException e) {
            System.out.println("For input string: \"" + commandArray[6] + "\" - not a valid amount.");
            return;
        }

        if(accountDatabase.contains(firstName, lastName, dob, acctType)) {
            System.out.println(firstName + " " + lastName +   " already has a " + commandArray[1] + " account.");
            return;
        }

        if(balance <= 0) {
            System.out.println("Initial deposit cannot be 0 or negative.");
            return;
        } else if(balance < 2000 && acctType.equals(AccountType.MONEY_MARKET)) {
            System.out.println("Minimum of $2,000 to open a Money Market account.");
            return;
        }



        Account account = getAccount(firstName, lastName, dob, branch, acctType);

        account.deposit(balance);


        accountDatabase.add(account);
        System.out.println(account.getAccountNumber().getType() +  " account " + account.getAccountNumber() + " has been opened.");

    }

    private static AccountType getAccountType (String type) {
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

    private static Branch getBranch(String branchName) {
        Branch branch = null;
        try {
            branch = Branch.valueOf(branchName.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(branchName + " - invalid branch.");
        }
        return branch;

    }

    private static Account getAccount(String firstName, String lastName, Date dateOfBirth, Branch branch, AccountType acctType) {
        Profile holder = getProfile(firstName, lastName, dateOfBirth);
        return new Account(branch, acctType, holder);
    }

    private static Profile getProfile(String firstName, String lastName, Date dateOfBirth) {
        return new Profile(firstName, lastName, dateOfBirth);
    }

    private static Date getDate(String date) {
        String[] dateParts = date.split("/");
        int month = Integer.parseInt(dateParts[0]);
        int day = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        return new Date(year, month, day);
    }


    private static void closeAccount(String[] commandArray) {
        if(commandArray.length == 2) {
            accountDatabase.remove(new Account(new AccountNumber(commandArray[1])));
        }
        else {
            String firstName = commandArray[1];
            String lastName = commandArray[2];
            Date dateOfBirth = getDate(commandArray[3]);
            accountDatabase.remove(firstName, lastName, dateOfBirth);
        }
    }


    private static void depositMoney(String[] commandArray) {
        AccountNumber accountNumber = new AccountNumber(commandArray[1]);
        accountDatabase.deposit(accountNumber, Double.parseDouble(commandArray[2]));
    }

    private static void withdrawMoney(String[] commandArray) {
        AccountNumber accountNumber = new AccountNumber(commandArray[1]);
        accountDatabase.withdraw(accountNumber, Double.parseDouble(commandArray[2]));
    }
    public static void main(String[] args) {

    }


}

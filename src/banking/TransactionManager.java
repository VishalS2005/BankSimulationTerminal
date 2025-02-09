package banking;

import java.util.Scanner;

/**
 * Transaction Manager class that reads in transactions from the command line
 * Depending on the action, completes transaction or does not execute it
 *
 @author Vishal Saravanan, Yining Chen
 */

public class TransactionManager {
    private static AccountDatabase accountDatabase = new AccountDatabase();

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            String[] commandArray = command.split("\\s+");
            if (commandArray.length == 0) continue; // Skip empty input
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
        AccountType acctType;
        String typeToken = commandArray[1].toLowerCase();
        switch (typeToken) {
            case "checking":
                acctType = AccountType.CHECKING;
                break;
            case "savings":
                acctType = AccountType.REGULAR_SAVINGS;
                break;
            case "moneymarket":
                acctType = AccountType.MONEY_MARKET_SAVINGS;
                break;
            default:  {
                System.out.println(typeToken + "- invalid account type!");
                return;
            }
        }

        Branch branch;
        try {
            branch = Branch.valueOf(commandArray[2].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(commandArray[2] + "- invalid branch!");
            return;
        }

        Account account = getAccount(commandArray, branch, acctType);

        accountDatabase.add(account);

    }

    private static Account getAccount(String[] commandArray, Branch branch, AccountType acctType) {
        String firstName = commandArray[3];
        String lastName = commandArray[4];
        String dateOfBirth = commandArray[5];
        String[] dobParts = dateOfBirth.split("/");
        int month = Integer.parseInt(dobParts[0]);
        int day = Integer.parseInt(dobParts[1]);
        int year = Integer.parseInt(dobParts[2]);
        Date dob = new Date(year, month, day);
        Profile holder = new Profile(firstName, lastName, dob);
        int deposit = Integer.parseInt(commandArray[6]);
        return new Account(branch, acctType, holder, deposit);
    }


    private static void closeAccount(String[] commandArray) {

    }

    private static void depositMoney(String[] commandArray) {

    }

    private static void withdrawMoney(String[] commandArray) {

    }
    public static void main(String[] args) {

    }


}

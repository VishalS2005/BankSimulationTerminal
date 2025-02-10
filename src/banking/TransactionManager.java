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
    private static final AccountDatabase accountDatabase = new AccountDatabase();

    private static DecimalFormat df = new DecimalFormat("#,##0.00");



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
                System.out.println(firstName + dateOfBirth +  " does not have any accounts in the database.");
                return;
            }
            accountDatabase.remove(firstName, lastName, dateOfBirth);
            System.out.println("All accounts for " + firstName + " " + lastName + " " + dateOfBirth + " are closed and moved to archive; balance set to 0.");
        }
    }


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

    public static void main(String[] args) {

    }


}

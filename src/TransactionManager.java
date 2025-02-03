import java.util.Scanner;

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
                System.out.println("Invalid command. Try again.");
        }
    }

    private static void openAccount(String[] commandArray) {

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

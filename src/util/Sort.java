package util;

import banking.Account;
import banking.AccountDatabase;

public class Sort {
    public static void account(AccountDatabase list, char key) {
        switch (key) {
            case 'B':
                sortByBranch(list);
                break;
            case 'H':
                sortByHolder(list);
                break;
            case 'T':
                sortByType(list);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort key");
        }
    }

    private static void sortByBranch(AccountDatabase list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if ((list.get(j).compareByBranch(list.get(j + 1))) > 0) {
                    Account temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    private static void sortByHolder(AccountDatabase list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if ((list.get(j).compareTo(list.get(j + 1))) > 0) {
                    Account temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    private static void sortByType(AccountDatabase list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                int typeComparison = list.get(j).compareByAccountType(list.get(j + 1));
                if (typeComparison > 0 || ( typeComparison == 0
                        && (list.get(j).getAccountNumber().compareTo(list.get(j + 1).getAccountNumber()) > 0))) {
                    Account temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}

package util;

import banking.Account;
import banking.AccountDatabase;

public class Sort {
    public static void account(AccountDatabase list, char key) {
        for (int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (compareAccounts(list.get(j), list.get(minIndex), key) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Account temp = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, temp);
            }
        }
    }

    private static int compareAccounts(Account a1, Account a2, char key) {
        if (key == 'B') {
            return a1.compareByBranch(a2);
        } else if (key == 'H') {
            return a1.compareTo(a2);
        } else if (key == 'T') {
            int typeComparison = a1.compareByAccountType(a2);
            if (typeComparison == 0) {
                return a1.getAccountNumber().compareTo(a2.getAccountNumber());
            }
            return typeComparison;
        } else {
            throw new IllegalArgumentException("Invalid sort key: " + key);
        }
    }
}

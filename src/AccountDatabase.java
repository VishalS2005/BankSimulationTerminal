public class AccountDatabase {
    private Account [] accounts;
    private int size;
    private Archive archive; //a linked list of closed account

    public AccountDatabase() {
        this.accounts = new Account[4];
        this.size = 0;
        this.archive = new Archive();
    }


    private int find(Account account) {
        for(int i = 0; i < size; i++) {
            if(accounts[i].equals(account)) {
                return i;
            }
        }
        return -1;
    } //return the index or -1 not found.

    private void grow() {
        Account[] newAccounts = new Account[accounts.length + 4];
        for(int i = 0; i < size; i++) {
            newAccounts[i] = accounts[i];
        }
        accounts = newAccounts;
    } //increase the array capacity by 4


    public boolean contains(Account account) {
        for(int i = 0; i < size; i++) {
            if(accounts[i].equals(account)) {
                return true;
            }
        }
        return false;
    } //check before add/remove


    public void add(Account account) {
        if(contains(account)) {
            return;
        }
        if(size == accounts.length) {
            grow();
        }

        accounts[size] = account;
        size++;
    } //add to end of array

    public void remove(Account account) {
        int index = find(account);
        if(index == -1) {
            return;
        }
        archive.add(accounts[index]);
        accounts[index] = accounts[size - 1];
        accounts[size - 1] = null;
        size--;

    }//replace it with the last item


    public boolean withdraw(AccountNumber number, double amount) {
        return false; // implement later
    }


    public void deposit(AccountNumber number, double amount) {
        for (int i = 0; i < size; i++) {
            if (accounts[i].getAccountNumber().equals(number)) {
                accounts[i].deposit(amount); // Perform deposit
                return;
            }
        }
    }


    public void printArchive() {
        archive.print();
    }//print closed accounts

    public void printByBranch() {
        //implement later
    }
    public void printByHolder() {
        //implement later
    }
    public void printByType() {
        //implement later
    }

    public void print() {
        for(int i = 0; i < size; i++) {
            System.out.println(accounts[i].toString());
        }
    }
}

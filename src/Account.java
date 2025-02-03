
public class Account implements Comparable<Account> {
    private AccountNumber number;
    private Profile holder;
    private double balance;
    public void withdraw(double amount) {} //to update the balance
    public void deposit(double amount) {} //to update the balance

    @Override
    public int compareTo(Account o) {
        return 0;
    }
}

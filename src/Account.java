
public class Account implements Comparable<Account> {
    private AccountNumber number;
    private Profile holder;
    private double balance;



    public void withdraw(double amount) {
        this.balance -= amount;
    } //to update the balance
    public void deposit(double amount) {
        this.balance += amount;
    } //to update the balance

    @Override
    public int compareTo(Account other) {
        return 0; // Fix later
    }

    @Override
    public String toString() {
        return "Account#[" + number + "] Holder[" + holder + "] Balance[" + balance + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account a = (Account) obj;
            return a.number == this.number;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}

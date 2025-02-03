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
    public AccountNumber getAccountNumber() {
        return this.number;
    }

    @Override
    public int compareTo(Account other) {
        if (this.balance > other.balance)
            return 1;
        if (this.balance < other.balance)
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Account#[" + number + "] Holder[" + holder + "] Balance[" + balance + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account other = (Account) obj;
            return other.number == this.number;
        }
        return false;
    }
}

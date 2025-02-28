package banking;

public class MoneyMarket extends Savings {
    /**
     * Number of withdrawals in the current statement cycle.
     */
    private int withdrawal = 0;

    public MoneyMarket(Branch branch, AccountType type, Profile holder, double balance) {
        super(branch, type, holder, balance);
        if(balance >= 5000) {
            this.isLoyal = true;
        }
    }

    @Override
    public double interestRate() {
           return isLoyal ? 0.0375 : 0.035;
    }

    @Override
    public double interest() {
        return balance * this.interestRate() / 12;
    }

    @Override
    public double fee() {
        return this.balance >= 2000 ? 0 : 25 + this.withdrawal > 3 ? 10 : 0;
    }

    @Override
    public void withdraw(double amount) {
        withdrawal++;
        super.withdraw(amount);

        if (this.getBalance() < 5000) {
            this.setIsLoyal(false);
        }
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        if (this.getBalance() >= 5000) {
            this.setIsLoyal(true);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Withdrawal[" + withdrawal + "]";
    }
}

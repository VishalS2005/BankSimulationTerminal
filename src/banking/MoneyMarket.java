package banking;

import util.Date;

public class MoneyMarket extends Savings {
    /**
     * Number of withdrawals in the current statement cycle.
     */
    private int withdrawal = 0;

    private static final double WITHDRAWAL_THRESHOLD = 3;

    private static final double WITHDRAWAL_FEE = 10;

    private static final double ACCOUNT_FEE = 25;

    private static final double LOYALTY_THRESHOLD = 5000;

    private static final double FEE_THRESHOLD = 2000;

    private static final double LOYAL_INTEREST_RATE = 0.0375;

    private static final double NOT_LOYAL_INTEREST_RATE = 0.035;

    public MoneyMarket(Branch branch, AccountType type, Profile holder, double balance) {
        super(branch, type, holder, balance);
        if(balance >= LOYALTY_THRESHOLD) {
            this.isLoyal = true;
        }
    }

    @Override
    public double interestRate() {
           return isLoyal ? LOYAL_INTEREST_RATE : NOT_LOYAL_INTEREST_RATE;
    }

    @Override
    public double fee() {
        return (this.balance >= FEE_THRESHOLD ? NO_FEE : ACCOUNT_FEE) + (this.withdrawal > WITHDRAWAL_THRESHOLD ? WITHDRAWAL_FEE: NO_FEE);
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        withdrawal++;

        if (this.getBalance() < LOYALTY_THRESHOLD) {
            this.setIsLoyal(false);
        }
    }

    @Override
    public void withdraw(Date date, Branch branch, double amount) {
        super.withdraw(date, branch, amount);
        withdrawal++;
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        if (this.getBalance() >= LOYALTY_THRESHOLD) {
            this.setIsLoyal(true);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Withdrawal[" + withdrawal + "]";
    }
}

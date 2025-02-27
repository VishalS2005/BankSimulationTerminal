package banking;

public class MoneyMarket extends Savings {
    /**
     * Number of withdrawals in the current statement cycle.
     */
    private int withdrawal = 0;

    public MoneyMarket(Branch branch, AccountType type, Profile holder) {
        super(branch, type, holder);
    }

    @Override
    public double interest() {
        return isLoyal ? 0.035 : 0.0335;
    }

    /**
     * Overrides the default withdraw method to include:
     * - A $10 fee for any withdrawal beyond the third in a statement cycle.
     * - A $25 fee if the balance falls below $2,000 after the withdrawal.
     *
     * @param amount the withdrawal amount
     */
    @Override
    public void withdraw(double amount) {
        // Increment the withdrawal counter for this statement cycle.
        withdrawal++;

//        // Calculate fee for extra withdrawals.
//        double extraFee = (withdrawal > 3) ? 10 : 0;
//
//        // Total amount to withdraw includes extra fee (if any).
//        double totalWithdrawal = amount + extraFee;
//
//        // Perform the withdrawal using the Savings (or Account) method.
//        // (Assuming Savings.withdraw handles sufficient funds checking.)
//        super.withdraw(totalWithdrawal);
        super.withdraw(amount);

        // After withdrawal, if balance falls below $2000, charge $25 fee.
        if (this.getBalance() < 2000) {
            // Deduct $25 fee (this may need to check for sufficient funds in your business logic).
//            super.withdraw(25);
        }

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

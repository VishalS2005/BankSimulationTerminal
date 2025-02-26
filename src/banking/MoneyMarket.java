package banking;

public class MoneyMarket extends Savings {
    /**
     * Number of withdrawals in the current statement cycle.
     */
    private int withdrawalCount = 0;

    public MoneyMarket(Branch branch, AccountType type, Profile holder) {
        super(branch, type, holder);
    }

    @Override
    public double interest() {
        return 0.035;
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
        withdrawalCount++;

        // Calculate fee for extra withdrawals.
        double extraFee = (withdrawalCount > 3) ? 10 : 0;

        // Total amount to withdraw includes extra fee (if any).
        double totalWithdrawal = amount + extraFee;

        // Perform the withdrawal using the Savings (or Account) method.
        // (Assuming Savings.withdraw handles sufficient funds checking.)
        super.withdraw(totalWithdrawal);

        // After withdrawal, if balance falls below $2000, charge $25 fee.
        if (this.getBalance() < 2000) {
            // Deduct $25 fee (this may need to check for sufficient funds in your business logic).
            super.withdraw(25);
        }
    }

    /**
     * (Optional) Resets the withdrawal count at the beginning of a new statement cycle.
     */
    public void resetWithdrawalCount() {
        withdrawalCount = 0;
    }

    /**
     * (Optional) Getter for the withdrawal count.
     */
    public int getWithdrawalCount() {
        return withdrawalCount;
    }
}

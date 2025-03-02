package banking;

/**
 * Extends the account class
 *
 *  @author Vishal Saravanan, Yining Chen
 */
public class Savings extends Account{
    /**
     * Loyal customer status.
     */
    protected boolean isLoyal;

    private static final double LOYAL_INTEREST_RATE = 0.0275;

    private static final double NOT_LOYAL_INTEREST_RATE = 0.025;

    private static final double ACCOUNT_FEE = 25;

    private static final double ACCOUNT_THRESHOLD = 500;

    public Savings(Branch branch, AccountType type, Profile holder, double balance) {
        super(branch, type, holder, balance);
        isLoyal = TransactionManager.accountDatabase.contains(holder, AccountType.CHECKING);
    }

    public void setIsLoyal(boolean isLoyal) {
        this.isLoyal = isLoyal;
    }

    @Override
    public double interestRate() {
        return isLoyal ? LOYAL_INTEREST_RATE : NOT_LOYAL_INTEREST_RATE;
    }

    @Override
    public double interest() {
        return balance * this.interestRate() / MONTHS_IN_YEAR;
    }

    @Override
    public double fee() {
        return balance >= ACCOUNT_THRESHOLD ? NO_FEE : ACCOUNT_FEE;
    }

    @Override
    public String toString() {
        return super.toString() + (isLoyal ? " [LOYAL]" : "");
    }

}

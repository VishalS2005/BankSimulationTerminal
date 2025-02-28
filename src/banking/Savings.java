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

    public Savings(Branch branch, AccountType type, Profile holder, double balance) {
        super(branch, type, holder, balance);
        isLoyal = TransactionManager.accountDatabase.contains(holder, AccountType.CHECKING);
    }

    public void setIsLoyal(boolean isLoyal) {
        this.isLoyal = isLoyal;
    }

    @Override
    public double interestRate() {
        return isLoyal ? 0.0275 : 0.025;
    }

    @Override
    public double interest() {
        return balance * this.interestRate() / 12;
    }

    @Override
    public double fee() {
        return balance >= 500 ? 0 : 25;
    }

    @Override
    public String toString() {
        return super.toString() + (isLoyal ? " [LOYAL]" : "");
    }

}

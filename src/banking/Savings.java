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

    public Savings(Branch branch, AccountType type, Profile holder) {
        super(branch, type, holder);
    }

    public double interest() {
        return 0.025;
    }

    public double fee() {
        return 25;
    }

    @Override
    public String toString() {
        return super.toString() + (isLoyal ? " [LOYAL] " : "");
    }

}

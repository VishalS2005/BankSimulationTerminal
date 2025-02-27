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
        return isLoyal ? 0.0275: 0.025 ;
    }

    public double fee() {
        return 25;
    }



    public void setIsLoyal(boolean isLoyal) {
        this.isLoyal = isLoyal;
    }

    @Override
    public String toString() {
        return super.toString() + (isLoyal ? " [LOYAL]" : "");
    }

}

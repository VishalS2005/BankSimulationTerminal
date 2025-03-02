package banking;

/**
 * The Checking class extends the Account class
 *
 * @author Vishal Saravanan, Yining Chen
 */
public class Checking extends Account{

    private static final double INTEREST_RATE = 0.015;

    private static final double FEE_THRESHOLD = 1000;

    private static final double ACCOUNT_FEE = 15;

    public Checking(Branch branch, AccountType type, Profile holder, double balance) {
        super(branch, type, holder, balance);
    }

    @Override
    public double interestRate() {
        return INTEREST_RATE;
    }

    @Override
    public double interest() {
        return this.interestRate() * balance / MONTHS_IN_YEAR;
    }

    @Override
    public double fee() {
        return this.balance >= FEE_THRESHOLD ? NO_FEE : ACCOUNT_FEE;
    }
}

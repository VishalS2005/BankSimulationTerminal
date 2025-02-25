package banking;

/**
 * Extends the savings class
 *
 *  @author Vishal Saravanan, Yining Chen
 */
public class MoneyMarket extends Savings {
    /**
     * Number of withdrawals
     */
    private int withdrawal;

    public MoneyMarket(Branch branch, AccountType type, Profile holder) {
        super(branch, type, holder);
    }

    @Override
    public double interest() {
        return 0.035;
    }
}

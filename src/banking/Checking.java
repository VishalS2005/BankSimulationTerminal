package banking;

/**
 * The Checking class extends the Account class
 *
 * @author Vishal Saravanan, Yining Chen
 */
public class Checking extends Account{
    public Checking(Branch branch, AccountType type, Profile holder, double balance) {
        super(branch, type, holder, balance);
    }

    @Override
    public double interestRate() {
        return 0.015;
    }

    @Override
    public double interest() {
        return this.interestRate() * balance / 12;
    }

    @Override
    public double fee() {
        return this.balance >= 1000 ? 0 : 15;
    }
}

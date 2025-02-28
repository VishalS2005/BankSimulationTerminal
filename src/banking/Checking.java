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

    public double interest() {
        return 0.015;
    }

    public double fee() {
        return this.balance >= 100 ? 15 : 0;
    }
}

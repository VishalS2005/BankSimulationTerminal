package banking;

/**
 * The Checking class extends the Account class
 *
 * @author Vishal Saravanan, Yining Chen
 */
public class Checking extends Account{
    public Checking(Branch branch, AccountType type, Profile holder) {
        super(branch, type, holder);
    }

    public double interest() {
        return 0.015;
    }

    public double fee() {
        return 15;
    }


}

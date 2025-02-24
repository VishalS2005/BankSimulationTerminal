package banking;

/**
 * The Checking class extends the Account class
 *
 * @author Vishal Saravanan, Yining Chen
 */
public abstract class Checking extends Account{
    public Checking(Branch branch, AccountType type, Profile holder) {
        super(branch, type, holder);
    }
}

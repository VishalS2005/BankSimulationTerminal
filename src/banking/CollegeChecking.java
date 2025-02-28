package banking;

/**
 * Extends the checking class
 *
 * @author Vishal Saravanan, Yining Chen
 */
public class CollegeChecking extends Checking{

    private Campus campus;

    public CollegeChecking(Branch branch, AccountType type, Profile holder, Campus campus, double balance) {
        super(branch, type, holder, balance);
        this.campus = campus;
    }


    @Override
    public double fee() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + " Campus[" + campus + "]";
    }
}


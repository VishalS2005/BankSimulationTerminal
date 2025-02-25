package banking;

/**
 * Extends the checking class
 *
 * @author Vishal Saravanan, Yining Chen
 */
public class CollegeChecking extends Checking{

    private Campus campus;

    public CollegeChecking(Branch branch, AccountType type, Profile holder, Campus campus) {
        super(branch, type, holder);
        this.campus = campus;
    }

    @Override
    public double fee() {
        return 0;
    }
}


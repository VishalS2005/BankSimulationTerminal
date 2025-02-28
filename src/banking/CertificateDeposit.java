package banking;

import util.Date;

/**
 * Extends the savings class
 *
 * @author Vishal Saravanan, Yining Chen
 */
public class CertificateDeposit extends Savings{
    private int term;
    private Date open;

    public CertificateDeposit(Branch branch, AccountType type, Profile holder, int term, Date open, double balance) {
        super(branch, type, holder, balance);
        this.term = term;
        this.open = open;
        this.isLoyal = false;
    }

    public Date getOpen() {
        return open;
    }

    @Override
    public double interest() {
        return switch (term) {
            case 3 -> 0.03;
            case 6 -> 0.0325;
            case 9 -> 0.035;
            case 12 -> 0.04;
            default -> 0;
        };
    }

    @Override
    public double fee() {
        return 0;
    }

    @Override
    public String toString() {
        // Calculate maturity date by adding the term (in months) to the open date.
        return super.toString()
                + " Term[" + term + "]"
                + " Date opened[" + open + "]"
                + " Maturity date[" + open.addMonths(open, term) + "]";
    }

}

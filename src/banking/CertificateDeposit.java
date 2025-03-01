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

    public int getTerm() {
        return term;
    }

    public double interestRate(Date closeDate) {
        int number = closeDate.daysAfter(open);
        if(number/30 <= 6) {
            return 0.03;
        }
        if(number/30 <= 9) {
            return 0.0325;
        }
        if(number/30 < 12) {
            return 0.035;
        }
        return -1;
   }


    @Override
    public double interestRate() {
        return switch (term) {
            case 3 -> 0.03;
            case 6 -> 0.0325;
            case 9 -> 0.035;
            case 12 -> 0.04;
            default -> -1;
        };
    }

    @Override
    public double interest() {
        return balance * interestRate() / 12;
    }


    @Override
    public String toString() {
        return super.toString()
                + " Term[" + term + "]"
                + " Date opened[" + open + "]"
                + " Maturity date[" + open.addMonths(term) + "]";
    }

}

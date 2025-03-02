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

    private static final double DAYS_IN_MONTHS = 30.0;

    public static final int THREE_MONTH_TERM = 3;

    private static final double THREE_MONTH_TERM_INTEREST_RATE = 0.03;

    public static final int SIX_MONTH_TERM = 6;

    private static final double SIX_MONTH_TERM_INTEREST_RATE = 0.0325;

    public static final int NINE_MONTH_TERM = 9;

    private static final double NINE_MONTH_TERM_INTEREST_RATE = 0.035;

    public static final int TWELVE_MONTH_TERM = 12;

    private static final double TWELVE_MONTH_TERM_INTEREST_RATE = 0.04;

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
        int daysHeld = closeDate.daysFrom(open);
        double monthsHeld = daysHeld / DAYS_IN_MONTHS;

        if (monthsHeld <= SIX_MONTH_TERM) {
            return THREE_MONTH_TERM_INTEREST_RATE;
        } else if (monthsHeld <= NINE_MONTH_TERM) {
            return SIX_MONTH_TERM_INTEREST_RATE;
        } else if (monthsHeld < TWELVE_MONTH_TERM) {
            return NINE_MONTH_TERM_INTEREST_RATE;
        } else {
            return TWELVE_MONTH_TERM_INTEREST_RATE;
        }
    }


    @Override
    public double interestRate() {
        return switch (term) {
            case THREE_MONTH_TERM -> THREE_MONTH_TERM_INTEREST_RATE;
            case SIX_MONTH_TERM -> SIX_MONTH_TERM_INTEREST_RATE;
            case NINE_MONTH_TERM -> NINE_MONTH_TERM_INTEREST_RATE;
            case TWELVE_MONTH_TERM -> TWELVE_MONTH_TERM_INTEREST_RATE;
            default -> throw new IllegalArgumentException("Invalid term");
        };
    }

    @Override
    public double interest() {
        return balance * interestRate() / MONTHS_IN_YEAR;
    }


    @Override
    public String toString() {
        return super.toString()
                + " Term[" + term + "]"
                + " Date opened[" + open + "]"
                + " Maturity date[" + open.addMonths(term) + "]";
    }

}

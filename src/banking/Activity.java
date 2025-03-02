package banking;

import util.Date;

import java.text.DecimalFormat;

public class Activity implements Comparable<Activity> {

    private Date date;

    private Branch location; //the location of the activity

    private char type; //D or W

    private double amount;

    private boolean atm; //true if this is made at an ATM (from the text file)

    private static final DecimalFormat df = new DecimalFormat("#,##0.00");


    public Activity(Date date, Branch location, char type, double amount, boolean atm) {
        this.date = date;
        this.location = location;
        this.type = type;
        this.amount = amount;
        this.atm = atm;
    }

    public char getType() {
        return type;
    }

    @Override
    public int compareTo(Activity o) {
        return date.compareTo(o.date);
    }
    public static void main(String[] args) {

    }

    @Override
    public String toString() {
        String transactionType = (type == 'D') ? "deposit" : "withdrawal";
        return date + "::" + location + (atm ? "[ATM]" : "" ) + "::" + transactionType + "::$" + df.format(amount);

    }
}

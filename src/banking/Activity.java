package banking;

import util.Date;

public class Activity implements Comparable<Activity> {

    private Date date;

    private Branch location; //the location of the activity

    private char type; //D or W

    private double amount;

    private boolean atm; //true if this is made at an ATM (from the text file)

    @Override
    public int compareTo(Activity o) {
        return date.compareTo(o.date);
    }
    public static void main(String[] args) {

    }
}

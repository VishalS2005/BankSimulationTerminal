package banking;

import java.util.Calendar;

/**
 * Date enum class has the information to provide the day, month, and year of a transaction
 * For the months of January, March, May, July, August, October, and December, each has 31 days;
 * April, June, September, and November each has 30 days;
 * February has 28 days in a non-leap year, and 29  days in a leap year
 * Quadrennial is 4 years, centennial is 100 years, and quartercentennial is 400 years
 *
 @author Vishal Saravanan, Yining Chen
 */

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int MONTH_OFFSET = 1;  // Calendar months are 0-based
    public static final int YEARS_TO_SUBTRACT = 18; // To check if the person is at least 18 years old
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;
    public static final int DAYS_IN_LONG_MONTH = 31;
    public static final int DAYS_IN_SHORT_MONTH = 30;
    public static final int DAYS_IN_FEBRUARY_NORMAL = 28;
    public static final int DAYS_IN_FEBRUARY_LEAP = 29;

    /**
     * Constructor for Date object
     *
     * @param year time period in #### format
     * @param month time period in ## format
     * @param day time period in ## format
     */
    public Date (int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Checks if the date provided by the user is on the calendar
     * Checks the month, the days in the month, and if it is a leap year
     *
     * @return true if the date is a valid date on the calendar
     * false otherwise
     */
    public boolean isValid() {
        if(this.month < Calendar.JANUARY + MONTH_OFFSET || this.month > Calendar.DECEMBER + MONTH_OFFSET) {
            return false;
        }
        int maxDays; //most amount of days in each month
        if(this.month == Calendar.FEBRUARY + MONTH_OFFSET) {
            if(isLeapYear()) {
                maxDays = DAYS_IN_FEBRUARY_LEAP;
            }
            else {
                maxDays = DAYS_IN_FEBRUARY_NORMAL;
            }
        }
        else if(this.month == Calendar.JANUARY + MONTH_OFFSET
                || this.month == Calendar.MARCH + MONTH_OFFSET
                || this.month == Calendar.MAY + MONTH_OFFSET
                || this.month == Calendar.JULY + MONTH_OFFSET
                || this.month == Calendar.AUGUST + MONTH_OFFSET
                || this.month == Calendar.OCTOBER + MONTH_OFFSET
                || this.month == Calendar.DECEMBER + MONTH_OFFSET) {
            maxDays = DAYS_IN_LONG_MONTH;
        }
        else {
            maxDays = DAYS_IN_SHORT_MONTH;
        }
        return this.day >= 1 && this.day <= maxDays;
    }

    /**
     * Checks if the Account holder is 18 years or older
     * Uses the Calendar library to import the actual date for calculations
     * Note: Month is 0-based in Calendar
     *
     * @return true if the account holder is 18 years or older
     * false otherwise
     */
    public boolean isEighteen() {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(this.year, this.month - MONTH_OFFSET, this.day); //must subtract 1 because month is 0-based
        today.add(Calendar.YEAR, -YEARS_TO_SUBTRACT);
        return !birthDate.after(today);
    }

    /**
     * Checks if the birthdate comes after today's date
     *
     * @return true if birthdate comes after today's date
     * false otherwise
     */
    public boolean isAfterToday() {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(this.year, this.month - MONTH_OFFSET, this.day);
        return birthDate.after(today);
    }

    /**
     * Checks if the year provided is a leap year
     * Every 4 years, 100 years, and 400 years is a leap year
     *
     * @return true if the year is a leap year
     * false otherwise
     */
    public boolean isLeapYear() {
        if(year % QUADRENNIAL != 0) {
            return false;
        }
        return year % CENTENNIAL != 0 || year % QUARTERCENTENNIAL == 0;
    }


    /**
     * Compares this Date to another Date
     *
     * @param other Date being compared with
     * @return difference of year/month/day
     */
    @Override
    public int compareTo(Date other) { //fix this method
        if(this.year != other.year) {
            return this.year - other.year;
        }
        else if(this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    /**
     * Converts the Date to Month/Day/Year format
     *
     * @return String representation of the date MM/DD/YYYY
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Compares two Dates for equality
     *
     * @param obj other Date being checked for equality
     * @return true if they are the same year, month, and day
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date other = (Date) obj;
            return this.year == other.year && this.month == other.month && this.day == other.day;
        }
        return false;
    }

    /** Test bed */
    public static void main(String[] args) {

    }
}

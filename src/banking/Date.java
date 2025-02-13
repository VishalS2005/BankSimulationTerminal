package banking;

import java.util.Calendar;

/**
 * The Date enum class has the information to provide the day, month, and year of a transaction.
 * For the months of January, March, May, July, August, October, and December, each has 31 days;
 * April, June, September, and November each has 30 days;
 * February has 28 days in a non-leap year, and 29 days in a leap year;
 * Quadrennial is 4 years, centennial is 100 years, and quarter centennial is 400 years.
 *
 @author Vishal Saravanan, Yining Chen
 */

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int MONTH_OFFSET = 1;  // Calendar months are 0-based
    public static final int MINIMUM_AGE_YEARS = 18; // To check if the person is at least 18 years old
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;
    public static final int DAYS_IN_LONG_MONTH = 31;
    public static final int DAYS_IN_SHORT_MONTH = 30;
    public static final int DAYS_IN_FEBRUARY_NORMAL = 28;
    public static final int DAYS_IN_FEBRUARY_LEAP = 29;

    /**
     * Creates a Date object.
     *
     * @param year time period in #### format
     * @param month time period in ## format
     * @param day time period in ## format
     */
    public Date (int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Checks if the date provided by the user is on the calendar.
     * Checks the month, the days in the month, and if it is a leap year.
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
     * Checks if the Account holder is 18 years or older.
     * Uses the Calendar library to import the actual date for calculations.
     * Note: Month is 0-based in Calendar.
     *
     * @return true if the account holder is 18 years or older
     * false otherwise
     */
    public boolean isEighteen() {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(this.year, this.month - MONTH_OFFSET, this.day); //must subtract 1 because month is 0-based
        today.add(Calendar.YEAR, -MINIMUM_AGE_YEARS);
        return !birthDate.after(today);
    }

    /**
     * Checks if the birthdate comes after today's date.
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
     * Checks if the year provided is a leap year.
     * Every 4 years, 100 years, and 400 years is a leap year.
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
     * Compares this Date to another Date.
     *
     * @param other Date being compared with
     * @return 0 if dates are equal
     * -1 if first date comes before the second
     * 1 if first date comes after the second
     */
    @Override
    public int compareTo(Date other) {
        if(this.year < other.year) {
            return -1;
        } else if (this.year > other.year) {
            return 1;
        } else if(this.month < other.month) {
            return -1;
        } else if(this.month > other.month) {
            return 1;
        } else if (this.day < other.day) {
            return -1;
        } else if(this.day > other.day) {
            return 1;
        }
        return 0;
    }

    /**
     * Converts the Date to Month/Day/Year format.
     *
     * @return String representation of the date MM/DD/YYYY
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Compares two Dates for equality.
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

    /**
     * Tests 6 different cases for the method, "isValid".
     * Validates the user input against predefined rules.
     * Test Bed: This method utilizes a test bed as described in the attached write-up.
     *
     *  @param args an array of command-line arguments passed to the program
     */
    public static void main(String[] args) {
        Date test1 = new Date(2, 29, 2023),
             test2 = new Date(8, 32, 2021),
             test3 = new Date(23, 29, 1900),
             test4 = new Date(9, -30, 2000),
             test5 = new Date(8, 23, 2008),
             test6 = new Date(2, 29, 2024);
        System.out.println("test1 result for " + test1 + ": " + test1.isValid());
        System.out.println("test2 result for " + test2 + ": " + test2.isValid());
        System.out.println("test3 result for " + test3 + ": " + test3.isValid());
        System.out.println("test4 result for " + test4 + ": " + test4.isValid());
        System.out.println("test5 result for " + test5 + ": " + test5.isValid());
        System.out.println("test6 result for " + test6 + ": " + test6.isValid());
    }
}

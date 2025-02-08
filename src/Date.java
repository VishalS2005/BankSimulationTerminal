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

    public static final int JANUARY   = 1;
    public static final int FEBRUARY  = 2;
    public static final int MARCH     = 3;
    public static final int APRIL     = 4;
    public static final int MAY       = 5;
    public static final int JUNE      = 6;
    public static final int JULY      = 7;
    public static final int AUGUST    = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER   = 10;
    public static final int NOVEMBER  = 11;
    public static final int DECEMBER  = 12;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;
    public static final int DAYS_IN_LONG_MONTH = 31;
    public static final int DAYS_IN_SHORT_MONTH = 30;
    public static final int DAYS_IN_FEBRUARY_NORMAL = 28;
    public static final int DAYS_IN_FEBRUARY_LEAP = 29;

    /**
     * Checks if the date provided by the user is on the calendar
     * Checks the month, the days in the month, and if it is a leap year
     *
     * @return true if the date is a valid date on the calendar
     * false otherwise
     */
    public boolean isValid() {
        if(this.month < JANUARY || this.month > DECEMBER) {
            return false;
        }
        int maxDays; //most amount of days in each month
        if(this.month == FEBRUARY) {
            if(isLeapYear()) {
                maxDays = DAYS_IN_FEBRUARY_LEAP;
            }
            else {
                maxDays = DAYS_IN_FEBRUARY_NORMAL;
            }
        }
        else if(this.month == JANUARY
                || this.month == MARCH
                || this.month == MAY
                || this.month == JULY
                || this.month == AUGUST
                || this.month == OCTOBER
                || this.month == DECEMBER) {
            maxDays = DAYS_IN_LONG_MONTH;
        }
        else {
            maxDays = DAYS_IN_SHORT_MONTH;
        }

        return this.day >= 1 && this.day <= maxDays;
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
}

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

        return day >= 1 && day <= maxDays;
    } //check if the date is a valid calendar date

    public boolean isLeapYear() {
        if(year % QUADRENNIAL != 0) {
            return false;
        }
        return year % CENTENNIAL != 0 || year % QUARTERCENTENNIAL == 0;
    }


    @Override
    public int compareTo(Date other) {
        if(this.year != other.year) {
            return this.year - other.year;
        }
        else if(this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date d = (Date) obj;
            return year == d.year && month == d.month && day == d.day;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}

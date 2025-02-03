import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int DAYS_IN_LONG_MONTH = 31;
    public static final int DAYS_IN_SHORT_MONTH = 30;
    public static final int DAYS_IN_FEBRUARY_NORMAL = 28;
    public static final int DAYS_IN_FEBRUARY_LEAP = 29;


    public boolean isValid() {


    } //check if the date is a valid calendar date



    @Override
    public int compareTo(Date o) {
        return 0;
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
}

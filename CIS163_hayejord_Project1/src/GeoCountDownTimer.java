import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**********************************************************************
 * CIS163 Project 1: Count Down Timer
 * Class GeoCountDownTimer creates a CountDownTimer with characteristics
 * day, month, and year; constrained to valid calendar year values greater than year 2021
 *
 * @author Jordan Hayes
 * @version 1
 **********************************************************************/

public class GeoCountDownTimer {

    /**private parameter month of object GeoCountDownTimer*/
    private int month;

    /**private parameter year of object GeoCountDownTimer*/
    private int year;

    /**private parameter day of object GeoCountDownTimer*/
    private int day;                                                                        //private parameter day of object Timer

    /**private parameter for minimum valid year of object GeoCountDownTimer*/
    private static final int MIN_YEAR = 2022;

    /**private parameter for maximum valid month of object GeoCountDownTimer according to Calendar Year*/
    private static final int MAX_MONTH = 12;

    /**private array containing the number of days in each much of the calendar year*/
    private final int[] daysInMonthArray = new int[] {31,28,31, 30,31,30, 31,31,30, 31,30,31};


    /*******************************************************************
     * Constructor creating default CountdownTimer
     ******************************************************************/
    public GeoCountDownTimer() {
        this(MIN_YEAR, 1, 1);
    }


    /*******************************************************************
     * Constructor initializing CountDownTimer instance variables
     * to year, month, and day
     * @param day -> day of the month
     * @param month -> month of the year
     * @param year -> year
     ******************************************************************/
    public GeoCountDownTimer(int year, int month, int day) {
        if(!isValidTimer(day, month, year)) {
            throw new IllegalArgumentException();
        }

        //call set methods to initialize object variables
        this.setMonth(month);
        this.setDay(day);
        this.setYear(year);
    }


    /*******************************************************************
     * Constructor initializing instance variables with the other Geo Timer
     * @param other -> CountDownTImer to initialize 'this'
     *              CountDownTimer's instance variables
     ******************************************************************/
    public GeoCountDownTimer(GeoCountDownTimer other) {

        if(!isValidTimer(other)) {
            throw new IllegalArgumentException();
        }

        this.year = other.year;                                                             //set the object parameter year to the other object year
        this.day = other.day;                                                               //set the object parameter day to the other object day
        this.month = other.month;                                                           //set the object parameter month to the other object month
    }


    /*******************************************************************
     *Getter method for this.day
     ******************************************************************/
    public int getDay() {
        return this.day;
    }


    /*******************************************************************
     *Getter method for this.month
     ******************************************************************/
    public int getMonth() {
        return this.month;
    }


    /*******************************************************************
     *Getter method for this.year
     ******************************************************************/
    public int getYear() {
        return this.year;
    }


    /*******************************************************************
     *Setter method for this.day
     * @param day -> day to set
     ******************************************************************/
    public void setDay(int day) {
        this.day = day;
    }


    /*******************************************************************
     *Setter method for this.month
     * @param month -> month to set
     ******************************************************************/
    public void setMonth(int month) {
        this.month = month;
    }


    /*******************************************************************
     *Setter method for this.year
     * @param year -> year to set
     ******************************************************************/
    public void setYear(int year) {
        this.year = year;
    }


    /*******************************************************************
     * Constructor creating CountDownTimer from valid Date string "mm/dd/yyyy"
     * @param geoDate -> String representing calendar date in form "mm/dd/yyyy"
     * @throws IllegalArgumentException -> if "null"
     * @throws IllegalArgumentException -> if any converted integers for
     *              the calendar date are invalid
     ******************************************************************/
    public GeoCountDownTimer(String geoDate) {

        if(geoDate.equals("null")) {
            throw new IllegalArgumentException();
        }

        //will delimit string by the slash expected in format
        String[] dateIntegerValues = geoDate.split("/");

        //throw an error for empty array of date values
        if(dateIntegerValues.length != 3)
            throw new IllegalArgumentException();

        //month is the first parsed integer; day, second; year, third
        int month = Integer.parseInt(dateIntegerValues[0]);
        int day = Integer.parseInt(dateIntegerValues[1]);
        int year = Integer.parseInt(dateIntegerValues[2]);

        //check if integer values are valid
        if(!isValidTimer(day, month, year)) {
            throw new IllegalArgumentException();
        }

        this.day = day;
        this.month = month;
        this.year = year;
    }


    /*******************************************************************
     * A method that determines whether 'this' CounDownTimer is equal
     * to other
     * @return true -> if “this” GeoCountDownTimer
     * object is exactly the same as the other object
     ******************************************************************/
    public boolean equals(Object other) {
        GeoCountDownTimer otherTimer = (GeoCountDownTimer) other;

        return this.compareTo(otherTimer) == 0;
    }


    /*******************************************************************
     * A method that implements boolean methods to compare two
     * CountDownTimers
     * @return 1 -> if “this” GeoCountDownTimer object is
     * greater than the other GeoCountDownTimer object;
     * @return -1 -> if the “this” GeoCountDownTimer object is
     * less than the other GeoCountDownTimer;
     * @return 0 -> if the “this” GeoCountDownTimer object is
     * equal to the other GeoCountDownTimer object.
     ******************************************************************/
    public int compareTo(GeoCountDownTimer other) {

        //determine if the instance parameters are equal individually
        //then find where they differ by comparison
        if (yearsAreEqual(this.year, other.year)) {
            if (monthsAreEqual(this.month, other.month)) {
                if(daysAreEqual(this.day, other.day)) {
                    return 0;
                }
                else if (this.day > other.day)
                    return 1;
                else
                    return -1;
            }
            else if (this.month > other.month)
                return 1;
            else
                return -1;
        }
        else if (this.year > other.year)
            return 1;

        return -1;
    }


    /*******************************************************************
     * A method that subtracts 1 day from the “this” GeoCountDownTimer object
     * used in loops to subtract multiple days, see
     * public void dec(int days)
     * constrains logic to valid dates
     ******************************************************************/
    public void dec() {

        this.day = this.day - 1;

        //this nest will constrain 'this' CountDownTimer to valid output
        //as it decrements the calendar date by using the previous value
        // to determine the valid current value
        if(this.day == 0) {
            if (this.month == 1) {
                this.year -= 1;
                this.month = MAX_MONTH;

                if(!isYearValid(this.year)) {
                    year = 2022;
                    month = 1;
                    day = 1;
                    throw new IllegalArgumentException();
                }
            }
            else if (this.month == 3) {
                if(isLeapYear(this.year))
                    daysInMonthArray[1] = 29;
                else
                    daysInMonthArray[1] = 28;

                this.month -= 1;
            }
            else {
                this.month -= 1;
            }
            this.day = daysInMonthArray[this.month - 1];
        }
    }


    /*******************************************************************
     * A method that subtracts the number of days from the “this”
     * GeoCountDownTimer object.
     * @param days -> days to decrement from the current calendar date
     ******************************************************************/
    public void dec(int days) {
        if(days < 0) {
            throw new IllegalArgumentException();
        }

        for(int i = 0; i < days; i ++)
        {
            this.dec();
        }
    }


    /*******************************************************************
     *A method that adds 1 day from the “this” GeoCountDownTimer object
     * used in loops to increment multiple days, see
     * public void inc(int days)
     * constrains logic to valid dates
     ******************************************************************/
    public void inc() {

        this.day = this.day + 1;

        //this nest will constrain 'this' CountDownTimer to valid output
        //as the date is incremented. If month is 2 (Feb), and it is a leapYear,
        // then the daysInMonths Array must be changed
        if (this.month == 2) {
            if(isLeapYear(this.year))
                daysInMonthArray[1] = 29;
            else
                daysInMonthArray[1] = 28;
        }

        //this loop ensures that the month and year will change
        //accordingly with days if the max number of days in the
        // month has been exceeded
        if(this.day > daysInMonthArray[this.month - 1]) {
            if (this.month == MAX_MONTH) {
                this.year = this.year + 1;
                this.month = 1;
            }
            else {
                this.month += 1;
            }
            this.day = 1;
        }
    }


    /*******************************************************************
     * A method that adds the number of days to the “this”
     * GeoCountDownTimer object.
     * @param days -> number of days to increment from current date
     ******************************************************************/
    public void inc(int days) {
        if(days < 0) {
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < days; i ++)
        {
            this.inc();
        }
    }


    /*******************************************************************
     * Method that returns a string that represents a
     * GeoCountDownTimer with the following format:  “monthName day, year”
     * @return geoCountDownDateString -> string containing the integer
     * values of the calendar date in “monthName day, year” format
     ******************************************************************/
    public String toString(){

        return nameMonths(this.month) + " " + this.day + ", " + this.year;
    }


    /*******************************************************************
     * Method that returns a string that represents a
     * GeoCountDownTimer with the following format:  “month/day/year”.
     * @return geoCountDownDateString -> string containing the integer
     * values of the calendar date in “month/day/year” format
     ******************************************************************/
    public String toDateString() {
        String geoCountDownDateString = this.month + "/" + this.day + "/" + this.year;
        return geoCountDownDateString;
    }


    /*******************************************************************
     * Method that loads a date from a file
     * @param filename -> name of file loaded
     ******************************************************************/
    public void load(String filename) {

        try {
            Scanner fileReader = new Scanner (new File(filename));

            month = fileReader.nextInt();
            day = fileReader.nextInt();
            year = fileReader.nextInt();

            System.out.println(this.toString());
        }
        catch(Exception error) {
            System.out.println("File not found ");
        }
    }


    /*******************************************************************
     * Method that saves the current date into a file
     * @param filename -> name of file saved
     ******************************************************************/
    public void save(String filename) {
        PrintWriter out = null;

        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        String savedDate = this.month + " " + this.day + " " + this.year;
        out.println(savedDate);
        out.close();
    }


    /*******************************************************************
     * Method calculating the number of days to go until the given date
     * @param fromDate -> given date in the form of String
     * @return daysUntilFromDate -> the number of days until fromDate
     * incremented until the strings are equal
     ******************************************************************/
    public int daysToGo(String fromDate) {

        int daysUntilFromDate = 0;

        if(fromDate.equals("null"))
            throw new IllegalArgumentException();

        GeoCountDownTimer t1 = new GeoCountDownTimer(fromDate);

        if(!isValidTimer(t1)) {
            throw new IllegalArgumentException();
        }

        // if the t1 reference object is less than 'this'.
        // we increment the t1 date each time the object
        // dates are not equal
        // vice versa if t1 is greater than 'this'
        // A day is subtracted until objects are equal
        // each time the objects are not equal,
        // variable daysUntilFromDate is incremented
        if(t1.compareTo(this) < 0)
            while(!t1.equals(this)){
                daysUntilFromDate++;
                t1.inc();
            }
        else
            while(!t1.equals(this)){
                daysUntilFromDate--;
                t1.dec();
            }
        return daysUntilFromDate;
    }


    /*******************************************************************
     * Method that calculates a CountDownTImer date 'n' days in the future
     * called by futureDateButton
     * @param n -> number of days to go out from the current date
     * @return s1 -> reference object copy of 'this', incremented n times
     ******************************************************************/
    public GeoCountDownTimer daysInFuture(int n) {

        GeoCountDownTimer s1 = new GeoCountDownTimer(this);

        if(n < 0) {
            n = n * -1;
            s1.inc(n);
        }
        else if (n > 0)
            s1.inc(n);
        else
            return s1;

        return s1;
    }


    /*******************************************************************
     * Boolean method to determine whether given years are equal
     * @param year1 -> year to compare with year2
     * @param year2 -> year to compare with year1
     ******************************************************************/
    public boolean yearsAreEqual(int year1, int year2) {
        return year1 == year2;
    }


    /*******************************************************************
     * Boolean method to determine whether given months are equal
     * @param month1 -> month to compare with month2
     * @param month2 -> month to compare with month1
     ******************************************************************/
    public boolean monthsAreEqual(int month1, int month2) {
        return month1 == month2;
    }


    /*******************************************************************
     * Boolean method to determine whether the given days are equal
     * @param day1 -> day to compare with day2
     * @param day2 -> day to compare with day1
     ******************************************************************/
    public boolean daysAreEqual(int day1, int day2) {
        return day1 == day2;
    }


    /*******************************************************************
     * Boolean method for determining whether the given year is a Leap Year
     * @param year -> year to determine whether is a leap year
     ******************************************************************/
    public boolean isLeapYear(int year) {
        if(year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else
                return true;
        }
        else
            return false;
    }


    /*******************************************************************
     * Boolean method to determine whether the CountDownTimer is valid
     * based on its day, month, and year
     * @param year -> year to determine whether valid
     * @param month -> month to determine whether valid
     * @param day  -> day to determine whether valid
     ******************************************************************/
    public boolean isValidTimer(int day, int month, int year) {
        return isYearValid(year) && isMonthValid(month) && isDayValid(day, month, year);
    }


    /*******************************************************************
     * Boolean method to determine whether the 'other'
     * CountDownTimer object is valid
     * @param other -> GeoCountDownTimer to determine whether valid
     ******************************************************************/
    public boolean isValidTimer(GeoCountDownTimer other) {
        return isYearValid(other.year) && isMonthValid(other.month) && isDayValid(other.day, other.month, other.year);
    }


    /*******************************************************************
     * Boolean Method that determines whether a day of the month is a valid date
     * @param day -> day to determine if valid
     * @param month -> month of year needed to determine the range of valid days
     * @param year -> year needed to determine range of valid days
     ******************************************************************/
    public boolean isDayValid(int day, int month, int year) {
        if(isLeapYear(year)) {
            //if it is a leap year, change daysInMonth Array according
            daysInMonthArray[1] = 29;
            return (0 < day) && (day <= daysInMonthArray[month - 1]);
        }
        else {
            //if it is NOT a leap year, change daysInMonth Array according
            daysInMonthArray[1] = 28;
            return (0 < day) && (day <= daysInMonthArray[month - 1]);
        }
    }


    /*******************************************************************
     * Boolean Method that determines whether a month of the year is a valid month
     * @param month -> month to determine if valid
     ******************************************************************/
    public boolean isMonthValid(int month) {
        return (0 < month) && (month <= MAX_MONTH);
    }


    /*******************************************************************
     * Boolean Method that determines whether the year is valid
     * must be greater than MIN_YEAR
     * @param year -> year to determine if valid
     ******************************************************************/
    public boolean isYearValid(int year) {
        return year >= MIN_YEAR;
    }


    /*******************************************************************
     * Method that determines the string output from the month
     * @param month -> month to determine which string to return
     * @return monthName -> String containing English month name
     ******************************************************************/
    public String nameMonths(int month) {
        String monthName = switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "Not a valid Month";
        };
        return monthName;
    }
}

/*
LC#1154
Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD, return the day number of the year.



Example 1:

Input: date = "2019-01-09"
Output: 9
Explanation: Given date is the 9th day of the year in 2019.
Example 2:

Input: date = "2019-02-10"
Output: 41
Example 3:

Input: date = "2003-03-01"
Output: 60
Example 4:

Input: date = "2004-03-01"
Output: 61


Constraints:

date.length == 10
date[4] == date[7] == '-', and all other date[i]'s are digits
date represents a calendar date between Jan 1st, 1900 and Dec 31, 2019.
 */
public class DayOfTheYear {
    public int dayOfYear(String date) {
        int[] days = {0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        String[] ss = date.split("-");
        int y = Integer.valueOf(ss[0]);
        int m = Integer.valueOf(ss[1]);
        int d = Integer.valueOf(ss[2]);
        return days[m] + d + leap(y, m);
    }

    int leap(int y, int m) {
        if (y % 100 == 0 && y % 400 == 0 && m > 2) {
            return 1;
        }
        if (y % 100 != 0 && y % 4 == 0 && m > 2) {
            return 1;
        }
        return 0;
    }
}

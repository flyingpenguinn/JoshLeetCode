/*
LC#1118
Given a year Y and a month M, return how many days there are in that month.



Example 1:

Input: Y = 1992, M = 7
Output: 31
Example 2:

Input: Y = 2000, M = 2
Output: 29
Example 3:

Input: Y = 1900, M = 2
Output: 28


Note:

1583 <= Y <= 2100
1 <= M <= 12
 */
public class NumberOfDaysInAMonth {
    int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int numberOfDays(int y, int m) {

        return isleap(y) && m == 2 ? days[m] + 1 : days[m];
    }

    boolean isleap(int y) {
        return (y % 100 != 0 && y % 4 == 0) || (y % 100 == 0 && y % 400 == 0);
    }
}

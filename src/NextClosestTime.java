import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
LC#681
Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

Example 1:

Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
Example 2:

Input: "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
 */
public class NextClosestTime {
    public String nextClosestTime(String time) {
        Set<Integer> set = new HashSet<>();
        int hint = 0;
        int mint = 0;
        for (int i = 0; i < time.length(); i++) {
            if (i == 2) {
                continue;
            }
            int cind = time.charAt(i) - '0';
            if (i <= 1) {
                hint = hint * 10 + cind;
            } else if (i >= 3) {
                mint = mint * 10 + cind;
            }
            set.add(cind);
        }
        int timeint = hint * 60 + mint;
        int day = 24 * 60;
        int mind = day * 10;
        int minint = -1;
        for (int h1 : set) {
            if (h1 > 2) {
                continue;
            }
            for (int h2 : set) {
                int hour = h1 * 10 + h2;
                if (hour >= 24) {
                    continue;
                }
                for (int m1 : set) {
                    if (m1 > 5) {
                        continue;
                    }
                    for (int m2 : set) {
                        int min = m1 * 10 + m2;
                        if (min >= 60) {
                            continue;
                        }
                        int cur = hour * 60 + min;
                        //  System.out.println(cs);
                        int diff = cur > timeint ? cur - timeint : cur - timeint + day;
                        if (diff < mind) {
                            mind = diff;
                            minint = cur;
                        }
                    }
                }
            }
        }
        int rmin = minint % 60;
        int rhour = (minint - rmin) / 60;
        String srhour = rhour < 10 ? "0" + rhour : String.valueOf(rhour);
        String srmin = rmin < 10 ? "0" + rmin : String.valueOf(rmin);
        return srhour + ":" + srmin;
    }
}

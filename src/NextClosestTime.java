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

    // traps: validate your time
    // return current when no closer time
    int[] seconds = {3600 * 10, 3600, 0, 60 * 10, 60};

    int oneday = 24 * 3600;

    public String nextClosestTime(String time) {
        int mindiff = 10000000;
        String mintime = null;
        int target = 0;
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < time.length(); i++) {
            if (i != 2) {
                int v = time.charAt(i) - '0';
                nums.add(v);
                target += v * seconds[i];
            }
        }
        for (int h1 : nums) {
            for (int h2 : nums) {
                if (h1 * 10 + h2 >= 24) {
                    continue;
                }
                for (int m1 : nums) {
                    for (int m2 : nums) {
                        if (m1 * 10 + m2 >= 60) {
                            continue;
                        }
                        int cur = h1 * seconds[0] + h2 * seconds[1] + m1 * seconds[3] + m2 * seconds[4];
                        if (cur != -1) {
                            // note this logic
                            int curdiff = cur < target ? cur + oneday - target : cur - target;
                            if (curdiff > 0 && curdiff < mindiff) {
                                mindiff = curdiff;
                                mintime = "" + h1 + h2 + ":" + m1 + m2;
                            }
                        }
                    }
                }
            }
        }
        // only use current time when no choice
        return mintime == null ? time : mintime;
    }
}

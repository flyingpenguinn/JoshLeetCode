import java.util.Arrays;

/*
LC#1288
Given a list of intervals, remove all intervals that are covered by another interval in the list. Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.

After doing so, return the number of remaining intervals.



Example 1:

Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.


Constraints:

1 <= intervals.length <= 1000
0 <= intervals[i][0] < intervals[i][1] <= 10^5
intervals[i] != intervals[j] for all i != j
 */
public class RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] a) {
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int i = 0;
        int n = a.length;
        int r = 0;
        while (i < n) {
            r++;
            int j = i + 1;
            while (j < n) {
                if (a[j][1] <= a[i][1]) {
                    j++;
                } else {
                    // if next one is not covered by i we can dump this one
                    // because if anything is covered by i it's covered by j too
                    break;
                }
            }
            i = j;
        }
        return r;
    }
}

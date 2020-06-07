import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
LC#56

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MergeIntervals {
    public int[][] merge(int[][] a) {
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int n = a.length;
        if (n == 0) {
            return new int[0][0];
        }
        List<int[]> r = new ArrayList<>();
        int start = a[0][0];
        int end = a[0][1];
        for (int i = 1; i < n; i++) {
            if (a[i][0] > end) {
                r.add(new int[]{start, end});
                // dont forget to open new ones!
                start = a[i][0];
                end = a[i][1];
            } else {
                end = Math.max(end, a[i][1]);
            }
        }
        // dont forget the last one
        r.add(new int[]{start, end});
        int[][] rr = new int[r.size()][2];
        for (int i = 0; i < r.size(); i++) {
            rr[i] = r.get(i);
        }
        return rr;
    }
}

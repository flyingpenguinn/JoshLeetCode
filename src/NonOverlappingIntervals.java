import java.util.Arrays;

/*
LC#435
Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.



Example 1:

Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:

Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:

Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Note:

You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 */
public class NonOverlappingIntervals {
    // same as pick the most...
    public int eraseOverlapIntervals(int[][] a) {
        if (a.length == 0) {
            return 0;
        }
        Arrays.sort(a, (x, y) -> Integer.compare(x[1], y[1]));
        int[] last = a[0];
        int r = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i][0] < last[1]) {
                r++;
            } else {
                last = a[i];
            }
        }
        return r;
    }
}

class NonOverlappingIntervalsSortStart {
    // we can also sort start and find out the min end
    public int eraseOverlapIntervals(int[][] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int end = a[0][1];
        int res = 1;
        for (int i = 1; i < n; i++) {
            if (a[i][0] >= end) {
                res++;
                end = a[i][1];
            } else {
                end = Math.min(end, a[i][1]);
            }
        }
        return n - res;
    }
}

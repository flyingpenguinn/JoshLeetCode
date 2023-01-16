import java.util.*;

/*

LC#57
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int i = 0;
        List<int[]> res = new ArrayList<>();
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            ++i;
        }
        int[] toadd = new int[]{newInterval[0], newInterval[1]};
        while (i < n && intervals[i][0] <= newInterval[1]) {
            toadd[0] = Math.min(toadd[0], intervals[i][0]);
            toadd[1] = Math.max(toadd[1], intervals[i][1]);
            ++i;
        }
        res.add(toadd);
        while (i < n) {
            res.add(intervals[i]);
            ++i;
        }
        int[][] rres = new int[res.size()][2];
        for (i = 0; i < res.size(); ++i) {
            rres[i] = res.get(i);
        }
        return rres;
    }
}

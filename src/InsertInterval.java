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
    public int[][] insert(int[][] ints, int[] t) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int[] it : ints) {
            tm.put(it[0], it[1]);
        }
        int start = t[0];
        int end = t[1];
        var next = tm.floorEntry(start);
        if (next == null) {
            next = tm.higherEntry(start);
        }
        int nstart = start;
        int nend = end;
        while (next != null && next.getKey() <= end) {
            if (next.getValue() >= start) {
                nstart = Math.min(nstart, next.getKey());
                nend = Math.max(nend, next.getValue());
                tm.remove(next.getKey());
            }
            next = tm.higherEntry(next.getKey());
        }
        tm.put(nstart, nend);
        int[][] res = new int[tm.size()][2];
        int ri = 0;
        for (int k : tm.keySet()) {
            res[ri++] = new int[]{k, tm.get(k)};
        }
        return res;
    }
}

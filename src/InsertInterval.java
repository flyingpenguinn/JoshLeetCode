import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
    // On, because we may change the return array...
    public int[][] insert(int[][] a, int[] ni) {
        List<int[]> r = new ArrayList<>();
        int n = a.length;
        if (n == 0) {
            r.add(ni);
            return toarray(r);
        }
        if (ni[1] < a[0][0]) {
            r.add(ni);
            addall(a, r, n, 0);
            return toarray(r);
        }
        if (ni[0] > a[n - 1][1]) {
            addall(a, r, n, 0);
            r.add(ni);
            return toarray(r);
        }
        int[] merged = ni;
        for (int i = 0; i < n; i++) {
            if (a[i][1] < ni[0]) {
                r.add(a[i]);
            } else if (a[i][0] > ni[1]) {
                if (merged != null) {
                    r.add(merged);
                    merged = null;
                    addall(a, r, n, i);
                    break;
                }
            } else {
                merged = new int[]{Math.min(a[i][0], merged[0]), Math.max(a[i][1], merged[1])};
            }
        }
        if (merged != null) {
            r.add(merged);
        }
        return toarray(r);
    }

    private void addall(int[][] a, List<int[]> r, int n, int from) {
        for (int i = from; i < n; i++) {
            r.add(a[i]);
        }
    }

    int[][] toarray(List<int[]> input) {
        int[][] r = new int[input.size()][2];
        for (int i = 0; i < input.size(); i++) {
            r[i] = input.get(i);
        }
        return r;
    }
}

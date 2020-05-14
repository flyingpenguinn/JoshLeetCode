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
    public int[][] merge(int[][] inter) {
        int n = inter.length;
        List<int[]> r = new ArrayList<>();
        if (n == 0) {
            return toarray(r);
        }
        Arrays.sort(inter, (a, b) -> Integer.compare(a[0], b[0]));
        int cs = inter[0][0];
        int ce = inter[0][1];

        for (int i = 1; i < n; i++) {
            if (inter[i][0] > ce) {
                r.add(new int[]{cs, ce});
                // start a new streak
                cs = inter[i][0];
                ce = inter[i][1];
            } else {
                ce = Math.max(ce, inter[i][1]);
            }
        }
        // dont forget the last
        r.add(new int[]{cs, ce});
        return toarray(r);
    }

    int[][] toarray(List<int[]> input) {
        int[][] r = new int[input.size()][2];
        for (int i = 0; i < input.size(); i++) {
            r[i] = input.get(i);
        }
        return r;
    }
}

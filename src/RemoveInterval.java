import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
LC#1272
Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers x such that a <= x < b.

We remove the intersections between any interval in intervals and the interval toBeRemoved.

Return a sorted list of intervals after all such removals.



Example 1:

Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
Output: [[0,1],[6,7]]
Example 2:

Input: intervals = [[0,5]], toBeRemoved = [2,3]
Output: [[0,2],[3,5]]


Constraints:

1 <= intervals.length <= 10^4
-10^9 <= intervals[i][0] < intervals[i][1] <= 10^9
 */
public class RemoveInterval {
    public List<List<Integer>> removeInterval(int[][] a, int[] rem) {
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));

        List<List<Integer>> r = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i][1] < rem[0]) {
                toList(a[i][0], a[i][1], r);

            } else if (a[i][0] > rem[1]) {
                toList(a[i][0], a[i][1], r);
            } else {
                if (a[i][0] <= rem[0] && a[i][1] <= rem[1]) {
                    toList(a[i][0], rem[0], r);
                } else if (a[i][0] >= rem[0] && a[i][1] >= rem[1]) {
                    toList(rem[1], a[i][1], r);
                } else if (a[i][0] <= rem[0] && a[i][1] >= rem[1]) {
                    toList(a[i][0], rem[0], r);
                    toList(rem[1], a[i][1], r);
                } else {
                    // do nothing, fully contained
                }
            }
        }
        return r;
    }

    private void toList(int s, int e, List<List<Integer>> r) {
        if (s != e) {
            List<Integer> list = new ArrayList<>();
            list.add(s);
            list.add(e);
            r.add(list);
        }
    }
}

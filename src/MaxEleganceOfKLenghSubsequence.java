import base.ArrayUtils;

import java.util.*;

public class MaxEleganceOfKLenghSubsequence {
    public long findMaximumElegance(int[][] a, int k) {
        int n = a.length;
        Arrays.sort(a, (x, y) -> Integer.compare(y[0], x[0]));
        long profit = 0;
        Set<Integer> seen = new HashSet<>();
        Deque<Integer> dupes = new ArrayDeque<>();
        for (int i = 0; i < k; ++i) {
            profit += a[i][0];
            if (seen.contains(a[i][1])) {
                // this is a dupe. it will not give us any more value so just mark it for replacement
                dupes.offerLast(a[i][0]);
            } else {
                seen.add(a[i][1]);
            }
        }
        // now res is top profits without any distinct element
        long res = profit + 1L * seen.size() * seen.size();

        for (int i = k; i < n; ++i) {
            if (dupes.isEmpty()) {
                // if no dupes yet then we were already maximizing the categories, we can break
                break;
            }
            if (seen.contains(a[i][1])) {
                // we are not going to do better than what we have with this category earlier
                continue;
            }
            // key: we substitute the last dupe, so that we get +1 type, and see if we can do better this time. note the last dupe must be picked before, which means we are not going to increase k
            int last = dupes.pollLast();
            profit -= last;
            profit += a[i][0];
            seen.add(a[i][1]);
            long cur = profit + 1L * seen.size() * seen.size();
            res = Math.max(res, cur);

        }
        return res;
    }

    /*

1
     */

    public static void main(String[] args) {
        System.out.println(new MaxEleganceOfKLenghSubsequence().findMaximumElegance(ArrayUtils.read("[[2,4],[2,2],[8,4],[5,1],[10,1]]"), 3));
    }
}

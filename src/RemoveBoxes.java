import base.ArrayUtils;

import java.util.*;

/*
LC#546
Given several boxes with different colors represented by different positive numbers.
You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
Find the maximum points you can get.

Example 1:
Input:

[1, 3, 2, 2, 2, 3, 4, 3, 1]
Output:
23
Explanation:
[1, 3, 2, 2, 2, 3, 4, 3, 1]
----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
----> [1, 3, 3, 3, 1] (1*1=1 points)
----> [1, 1] (3*3=9 points)
----> [] (2*2=4 points)
Note: The number of boxes n would not exceed 100.
 */
public class RemoveBoxes {
    // given a mergable block,do it now or wait. On^4 complexity, but using memoization it's still fast
    // @Liurujia 9-27p298
    int[][][] dp;

    public int removeBoxes(int[] a) {
        int n = a.length;
        dp = new int[n + 1][n + 1][n + 1];
        return dor(a, 0, n - 1, 0);
    }

    // from i to j, there are k boxes on the right of j that has the same type with j
    int dor(int[] a, int i, int j, int k) {
        if (i > j) {
            return 0;
        }
        int end = a[j];
        int p = j - 1;
        while (p >= i && a[p] == end) {
            p--;
        }
        if (dp[i][j][k] != 0) {
            return dp[i][j][k];
        }
        int diffstart = p;
        int rights = j - diffstart + k;
        // i... p != end, p+1....j == end, and all == end
        // if we don't single this out we end up solving the same ijk again...
        int max = dor(a, i, diffstart, 0) + rights * rights;
        while (p >= i) {
            if (a[p] == end && a[p + 1] != end) {
                // i... p == end, p+1....diffstart != end.
                // i...p can be hooked onto rights, if we take away the middle p+1...diffstart ones
                int cur = dor(a, i, p, rights) + dor(a, p + 1, diffstart, 0);
                max = Math.max(max, cur);
            }
            p--;
        }
        dp[i][j][k] = max;
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveBoxes().removeBoxes(ArrayUtils.read1d("[9, 2, 4, 4, 6, 5, 8, 4, 8, 6, 9, 6, 2, 8, 6, 4, 1, 9, 5]")));
    }
}

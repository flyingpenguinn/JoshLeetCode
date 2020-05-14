import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/*
LC#975
You are given an integer array A.  From some starting index, you can make a series of jumps.  The (1st, 3rd, 5th, ...) jumps in the series are called odd numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even numbered jumps.

You may from index i jump forward to index j (with i < j) in the following way:

During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j such that A[i] <= A[j] and A[j] is the smallest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index j such that A[i] >= A[j] and A[j] is the largest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
(It may be the case that for some index i, there are no legal jumps.)
A starting index is good if, starting from that index, you can reach the end of the array (index A.length - 1) by jumping some number of times (possibly 0 or more than once.)

Return the number of good starting indexes.



Example 1:

Input: [10,13,12,14,15]
Output: 2
Explanation:
From starting index i = 0, we can jump to i = 2 (since A[2] is the smallest among A[1], A[2], A[3], A[4] that is greater or equal to A[0]), then we can't jump any more.
From starting index i = 1 and i = 2, we can jump to i = 3, then we can't jump any more.
From starting index i = 3, we can jump to i = 4, so we've reached the end.
From starting index i = 4, we've reached the end already.
In total, there are 2 different starting indexes (i = 3, i = 4) where we can reach the end with some number of jumps.
Example 2:

Input: [2,3,1,1,4]
Output: 3
Explanation:
From starting index i = 0, we make jumps to i = 1, i = 2, i = 3:

During our 1st jump (odd numbered), we first jump to i = 1 because A[1] is the smallest value in (A[1], A[2], A[3], A[4]) that is greater than or equal to A[0].

During our 2nd jump (even numbered), we jump from i = 1 to i = 2 because A[2] is the largest value in (A[2], A[3], A[4]) that is less than or equal to A[1].  A[3] is also the largest value, but 2 is a smaller index, so we can only jump to i = 2 and not i = 3.

During our 3rd jump (odd numbered), we jump from i = 2 to i = 3 because A[3] is the smallest value in (A[3], A[4]) that is greater than or equal to A[2].

We can't jump from i = 3 to i = 4, so the starting index i = 0 is not good.

In a similar manner, we can deduce that:
From starting index i = 1, we jump to i = 4, so we reach the end.
From starting index i = 2, we jump to i = 3, and then we can't jump anymore.
From starting index i = 3, we jump to i = 4, so we reach the end.
From starting index i = 4, we are already at the end.
In total, there are 3 different starting indexes (i = 1, i = 3, i = 4) where we can reach the end with some number of jumps.
Example 3:

Input: [5,1,3,4,2]
Output: 3
Explanation:
We can reach the end from starting indexes 1, 2, and 4.


Note:

1 <= A.length <= 20000
0 <= A[i] < 100000
 */

public class OddEvenJump {
    // Key is to use treemap to find celing and floor, from back to front

    int[][] dp;
    int[] smaller;
    int[] bigger;

    public int oddEvenJumps(int[] a) {
        int n = a.length;
        dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        smaller = new int[n];
        Arrays.fill(smaller, -1);
        bigger = new int[n];
        Arrays.fill(bigger, -1);
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = n - 1; i >= 0; i--) {
            Integer nb = tm.ceilingKey(a[i]);
            if (nb != null) {
                bigger[i] = tm.get(nb);
            }
            Integer sm = tm.floorKey(a[i]);
            if (sm != null) {
                smaller[i] = tm.get(sm);
            }
            tm.put(a[i], i);
        }
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (good(i, 1, a) == 1) {
                r++;
            }
        }
        return r;
    }

    int good(int i, int oe, int[] a) {
        int n = a.length;
        if (i == n - 1) {
            return 1;
        }
        if (dp[i][oe] != -1) {
            return dp[i][oe];
        }
        int j = 0;
        if (oe == 0) {
            // even
            j = smaller[i];

        } else {
            j = bigger[i];
        }
        int rt = j == -1 ? 2 : good(j, oe ^ 1, a);
        dp[i][oe] = rt;
        return rt;
    }

    public static void main(String[] args) {
        OddEvenJump oej = new OddEvenJump();
        int[] arr = {10, 13, 12, 14, 15};
        System.out.println(oej.oddEvenJumps(arr));
        int[] arr2 = {2, 3, 1, 1, 4};
        System.out.println(oej.oddEvenJumps(arr2));
    }
}

class OddEvenJumpNonRecursion {
    // Key is to use treemap to find celing and floor, from back to front

    public int oddEvenJumps(int[] a) {
        int n = a.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[][] dp = new int[n][2];

        int r = 1;
        dp[n - 1][0] = dp[n - 1][1] = 1;
        map.put(a[n-1], n-1);
        for (int i = n - 2; i >= 0; i--) {
            Integer higher = map.ceilingKey(a[i]);
            int nodd = higher == null ? -1 : map.get(higher);
            Integer lower = map.floorKey(a[i]);
            int neven= lower == null ? -1 : map.get(lower);
            if (neven != -1) {
                dp[i][0] = dp[neven][1];
            }
            if (nodd != -1) {
                dp[i][1] = dp[nodd][0];
                r += dp[i][1]; // good as long as odd is reachable
            }
            map.put(a[i], i);
        }
        return r;
    }
}
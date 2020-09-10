import base.ArrayUtils;

import java.util.Arrays;

/*
LC#1478
Given the array houses and an integer k. where houses[i] is the location of the ith house along a street, your task is to allocate k mailboxes in the street.

Return the minimum total distance between each house and its nearest mailbox.

The answer is guaranteed to fit in a 32-bit signed integer.



Example 1:



Input: houses = [1,4,8,10,20], k = 3
Output: 5
Explanation: Allocate mailboxes in position 3, 9 and 20.
Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5
Example 2:



Input: houses = [2,3,5,12,18], k = 2
Output: 9
Explanation: Allocate mailboxes in position 3 and 14.
Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.
Example 3:

Input: houses = [7,4,6,1], k = 1
Output: 8
Example 4:

Input: houses = [3,6,14,10], k = 4
Output: 0


Constraints:

n == houses.length
1 <= n <= 100
1 <= houses[i] <= 10^4
1 <= k <= n
Array houses contain unique integers.
 */
public class AllocateMailbox {
    // optimal solution is to put mail boxes on each house, not in between! and for k==1, optimal solution is to put at the median
    // each time bite a "sphere of influence of some mailbox" off. and dp on the remaining. in that sphere, we put the mailbox at the median
    // then precalculate the median
    // note we can get rid of the influence of an earlier mailbox because we are biting sphere off
    private Integer[][] dp;

    public int minDistance(int[] a, int k) {
        Arrays.sort(a);
        int n = a.length;
        int[] sum = new int[n];
        dp = new Integer[n][k + 1];
        sum[0] = a[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + a[i];
        }
        return domin(a, sum, 0, k);
    }

    private int Max = 10000000;

    // cover houses from i with k boxes. previous houses are covered by earlier boxes
    private int domin(int[] a, int[] sum, int i, int k) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (k == 0) {
            return Max;
        }
        if (dp[i][k] != null) {
            return dp[i][k];
        }
        int min = Max;
        for (int j = i; j < n; j++) {
            // the boundary of the mail box area is i...j
            int mid = (i + j) / 2;
            int n2 = j - mid;
            int n1 = mid - i;
            int p2 = sum[j] - sum[mid];
            int p1 = mid == 0 ? 0 : (sum[mid - 1] - (i == 0 ? 0 : sum[i - 1]));
            // a1-a2+a3-a2+a4-a2+a5-a2
            // so we have j-mid bigger numbers, and mid-i smaller numbers.
            // each smaller gives extra -a[mid] while bigger number gives +a[mid]
            int cur = (n1 - n2) * a[mid] + (p2 - p1) + domin(a, sum, j + 1, k - 1);
            min = Math.min(min, cur);
        }
        dp[i][k] = min;
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new AllocateMailbox().minDistance(ArrayUtils.read1d("[1,4,8,10,20]"), 3));
    }
}

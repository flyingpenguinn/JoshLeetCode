import base.ArrayUtils;

import java.util.*;

/*
LC#689
In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:

Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.


Note:

nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).
 */
public class MaxSumOfThreeNonOverlappingSubarrays {
    // enumerate the middle subarray, similar to best time buy sell stock III
    public int[] maxSumOfThreeSubarrays(int[] a, int k) {
        int n = a.length;
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
        }
        // max left subarray of size k, their end point
        int[] maxleft = new int[n];
        int[] maxleftindex = new int[n];
        maxleft[k - 1] = sum[k - 1];
        maxleftindex[k - 1] = k - 1;
        for (int i = k; i < n; i++) {
            int segsum = sum[i] - sum[i - k];
            if (segsum > maxleft[i - 1]) {
                maxleft[i] = segsum;
                maxleftindex[i] = i;
            } else {
                // leco small one
                maxleft[i] = maxleft[i - 1];
                maxleftindex[i] = maxleftindex[i - 1];
            }
        }
        // max subarray with i as starting point
        int[] maxright = new int[n];
        int[] maxrightindex = new int[n];
        maxright[n - k] = sum[n - 1] - sum[n - k - 1];
        maxrightindex[n - k] = n - k;
        for (int i = n - k - 1; i >= 1; i--) {
            int segsum = sum[i + k - 1] - sum[i - 1];
            if (segsum >= maxright[i + 1]) {
                maxright[i] = segsum;
                maxrightindex[i] = i;
            } else {
                maxright[i] = maxright[i + 1];
                maxrightindex[i] = maxrightindex[i + 1];
            }
        }
        int max = 0;
        int[] r = new int[3];
        for (int i = 2 * k - 1; i + k < n; i++) {
            int curseg = sum[i] - sum[i - k];
            int leftsum = maxleft[i - k];
            int rightsum = maxright[i + 1];
            int cursum = curseg + leftsum + rightsum;
            if (cursum > max) {
                max = cursum;
                r[0] = maxleftindex[i - k] - k + 1;
                r[1] = i - k + 1;
                r[2] = maxrightindex[i + 1];
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MaxSumOfThreeNonOverlappingSubarrays().maxSumOfThreeSubarrays(ArrayUtils.read1d("1,2,1,2,6,7,5,1"), 2)));
        System.out.println(Arrays.toString(new MaxSumOfThreeNonOverlappingSubarrays().maxSumOfThreeSubarrays(ArrayUtils.read1d("1,2,1,2,2,2,2,2"), 2)));

    }
}

class MaxSumOfTreeDp {
    // O(3n) similar to stock problem
    int[] sum;
    int[][] dp;

    public int[] maxSumOfThreeSubarrays(int[] a, int k) {
        int n = a.length;
        sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
        }
        dp = new int[n + 1][4];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        int max = dom(0, 3, a, k);
        List<Integer> rr = solve(0, 3, max, k);
        int[] r = new int[rr.size()];
        for (int i = rr.size() - 1; i >= 0; i--) {
            r[rr.size() - 1 - i] = rr.get(i);
        }
        return r;
    }

    private List<Integer> solve(int i, int rem, int max, int k) {
        List<Integer> r = new ArrayList<>();
        int n = dp.length;
        if (rem == 0) {
            return r;
        }
        for (int j = i; j + k - 1 < n; j++) {
            if (dp[j][rem] == max) {
                int cursum = sum[j + k - 1] - (j == 0 ? 0 : sum[j - 1]);
                if (dp[j + k][rem - 1] == max - cursum) {
                    List<Integer> later = solve(j + k, rem - 1, max - cursum, k);
                    r.addAll(later);
                    r.add(j);
                    break;
                }
            }
        }
        return r;
    }

    // at index i, j remaining blocks
    private int dom(int i, int j, int[] a, int k) {
        if (i == a.length || j == 0) {
            dp[i][j] = 0;
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int nopick = dom(i + 1, j, a, k);
        int pick = 0;
        if (i + k - 1 < a.length) {
            int cursum = sum[i + k - 1] - (i == 0 ? 0 : sum[i - 1]);
            pick = cursum + dom(i + k, j - 1, a, k);
        }
        dp[i][j] = Math.max(nopick, pick);
        return dp[i][j];
    }
}
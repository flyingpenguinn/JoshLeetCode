/*
LC#698
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.



Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.


Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
 */
public class PartitionToKEqualSubsets {
    // actually similar to min incompatability. for each state we iterate its subset. upper bound is 3^n
    public boolean canPartitionKSubsets(int[] a, int k) {
        int n = a.length;
        int allsum = 0;
        for (int i = 0; i < n; i++) {
            allsum += a[i];
        }
        if (allsum % k != 0) {
            return false;
        }
        int t = allsum / k;
        int[] sums = new int[1 << n];
        boolean[] dp = new boolean[1 << n];
        for (int i = 0; i < (1 << n); i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    sum += a[j];
                }
            }
            sums[i] = sum;
            if (sum == t) {
                dp[i] = true;
            }
        }
        for (int i = 0; i < (1 << n); i++) {
            int sum = sums[i];
            if (sum % t != 0) {
                continue;
            }
            for (int j = i - 1; j > 0; j = ((j - 1) & i)) {
                if (dp[j] && dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}

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
    // dp on states. note times and cursum are incorporated in sum
    // need cursum and times to differentiate the boundary of each round
    private Boolean[][] dp;

    public boolean canPartitionKSubsets(int[] a, int k) {
        int n = a.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        if (sum % k != 0) {
            return false;
        }
        dp = new Boolean[n][1 << n];
        int t = sum / k;
        return cando(a, k, 0, 0, 0, 0, t);
    }

    private boolean cando(int[] a, int k, int i, int st, int times, int cursum, int target) {
        // hidden all subset sum driven by st
        // cursum is allsum % target
        // times is allsum / target
        int n = a.length;
        if (st + 1 == (1 << n)) {
            return times == k - 1;
            // make sure we really made k-1 cuts
        }
        if (cursum > target) {
            return false;
        }
        if (cursum == target) {
            return cando(a, k, 0, st, times + 1, 0, target);
        }
        if (i == n) {
// can't pick sum == target correctly for this cycle
            return false;
        }
        if (dp[i][st] != null) {
            return dp[i][st];
        }
        boolean nopick = cando(a, k, i + 1, st, times, cursum, target);
        if (nopick || ((st >> i) & 1) == 1) {
            dp[i][st] = nopick;
            return nopick;
        }
        int nst = st | (1 << i);
        boolean pick = cando(a, k, i + 1, nst, times, cursum + a[i], target);
        dp[i][st] = pick;
        return pick;
    }
}

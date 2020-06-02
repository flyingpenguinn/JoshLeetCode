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
    // here we can do on i, rk, rl but rl can be very large
    // when the number range is not possible to dp on, use the set to dp as it contains the remaining numbers and k times
    // similar to LC#473
    // use 1<<n to represent status is faster than bitset
    int[][] dp;

    public boolean canPartitionKSubsets(int[] a, int k) {
        int sum = 0;
        int n = a.length;
        dp = new int[n + 1][1 << n];
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        if (sum % k != 0) {
            return false;
        }
        int t = sum / k;
        return doc(a, 0, 0, k, t, t) == 1;
    }

    // we remain rk times, in this time we remain rl
    // rk and rl hidden in st
    int doc(int[] a, int i, int st, int rk, int rl, int t) {
        int n = a.length;
        if (rl == 0) {
            // must start from the beginning for the next round
            return doc(a, 0, st, rk - 1, t, t);
        }
        if (rk == 0) {
            return 1;
        }
        if (i == n) {
            return 2;
        }
        if (dp[i][st] != 0) {
            return dp[i][st];
        }
        int rt = 0;
        int nouse = doc(a, i + 1, st, rk, rl, t);
        if (nouse == 1) {
            rt = 1;
        } else if ((((st >> i) & 1) == 0) && rl - a[i] >= 0) {
            int nst = st | (1 << i);
            rt = doc(a, i + 1, nst, rk, rl - a[i], t);
        }
        dp[i][st] = rt;
        return rt;
    }

    public static void main(String[] args) {
        int[] array = {2, 2, 2, 2, 3, 4, 5};
        System.out.println(new PartitionToKEqualSubsets().canPartitionKSubsets(array, 4));
    }
}

class PartitionKEqualSubsetSumsAnotherDp {
    // just need to dp on states. even k is hidden in the state. sum of nums in st / target = current k
    public boolean canPartitionKSubsets(int[] a, int k) {
        int n = a.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        if (sum % k != 0) {
            return false;
        }
        dp = new int[1 << n];
        int rt = doc(0, 0, a, k, sum / k);
        return rt == 1;
    }

    int[] dp;

    private int doc(int st, int cursum, int[] a, int k, int target) {
        int n = a.length;
        if (cursum == target) {
            return doc(st, 0, a, k - 1, target);
        }
        if (k == 1) {
            return 1;
        }
        if (st == ((1 << n) - 1)) {
            return 2;
        }

        if (cursum > target) {
            return 2;
        }
        if (dp[st] != 0) {
            return dp[st];
        }
        int rt = 2;
        for (int i = 0; i < n; i++) {
            if (((st >> i) & 1) == 0) {
                int nst = st | (1 << i);
                int pick = doc(nst, cursum + a[i], a, k, target);
                if (pick == 1) {
                    rt = 1;
                    break;
                }
            }
        }
        dp[st] = rt;
        return rt;
    }
}

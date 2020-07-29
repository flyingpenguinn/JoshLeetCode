import java.util.Arrays;

/*
LC#53
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {
    // kadane algo. can use this idea to expand to multiplication
    public int maxSubArray(int[] a) {
        int maxe = a[0];
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            maxe = Math.max(maxe + a[i], a[i]);
            max = Math.max(maxe, max);
        }
        return max;
    }
}

class MaxSubarraySumPrefixSum {
    // can't expand to multiplication as we could meet 0 there
    public int maxSubArray(int[] a) {
        int minprefix = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            max = Math.max(max, sum - minprefix);
            minprefix = Math.min(minprefix, sum);
        }
        return max;
    }
}

class MaxSubarraySumDp {
    int max = Integer.MIN_VALUE;
    int[] dp;

    public int maxSubArray(int[] a) {
        dp = new int[a.length];
        Arrays.fill(dp, -1);
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, dp(a, i));
        }
        return max;
    }

    private int dp(int[] a, int i) {
        // max ending at i. either concat at i-1 (hence the max ending at i-1), or self establish one.
        if (dp[i] != -1) {
            return dp[i];
        }
        int rt = a[i];
        if (i > 0) {
            rt = Math.max(rt, dp(a, i - 1) + a[i]);
        }
        dp[i] = rt;
        return dp[i];
    }
}

class MaxSubarrayDivideConquer {
    public int maxSubArray(int[] a) {
        return dod(a, 0, a.length - 1);
    }

    private int dod(int[] a, int l, int u) {
        if (l > u) {
            return Integer.MIN_VALUE;
        }
        if (l == u) {
            return a[l];
        }
        int mid = l + (u - l) / 2;
        int left = dod(a, l, mid);
        int right = dod(a, mid + 1, u);
        // it must pass middle, so try to extend to right and left get max
        int rmax = Integer.MIN_VALUE;
        int rsum = 0;
        for (int i = mid + 1; i <= u; i++) {
            rsum += a[i];
            rmax = Math.max(rmax, rsum);
        }
        int lmax = Integer.MIN_VALUE;
        int lsum = 0;
        for (int i = mid; i >= l; i--) {
            lsum += a[i];
            lmax = Math.max(lmax, lsum);
        }
        int midmax = lmax + rmax;
        return Math.max(left, Math.max(right, midmax));
    }
}
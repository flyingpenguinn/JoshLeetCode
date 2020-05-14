import java.util.Arrays;

/*
LC#410
Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 */
public class SplitArrayLargestSum {
    // key feature: if mid can do then mid+1 can do too.
    // if we set a number and fewer segments was generated, we should lower the number and try again. we try out until getting < k segments
    // usually min max problems can be solved by binary search
    public int splitArray(int[] a, int k) {
        int n = a.length;
        int sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            max = Math.max(max, a[i]);
        }
        int l = max;
        int u = sum;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (good(a, k, mid)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    boolean good(int[] a, int k, int t) {
        int n = a.length;
        int p = 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (sum + a[i] > t) {
                sum = 0;
                p++;
            }
            sum += a[i];
        }
        // < is good: we can make t smaller
        return p <= k;
    }
}

class SplitArrayLargestSumDp {
    // also typical dp: cut 1 segment off and see how it does in the rest of k-1 segments
    int[][] dp;
    int[] psum;

    public int splitArray(int[] a, int k) {
        int n = a.length;
        dp = new int[n][k + 1];
        psum = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            Arrays.fill(dp[i], -1);
            psum[i] = i == n - 1 ? a[i] : psum[i + 1] + a[i];
        }
        return dos(a, 0, k);
    }

    private int dos(int[] a, int i, int k) {
        if (dp[i][k] != -1) {
            return dp[i][k];
        }
        int n = a.length;
        if (k == 1) {
            return psum[i];
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int j = i; j <= n - k; j++) {
            sum += a[j];
            if (sum >= min) {
                // early break out!
                break;
            }
            int later = dos(a, j + 1, k - 1);
            int cur = Math.max(sum, later);
            min = Math.min(min, cur);
        }
        dp[i][k] = min;
        return min;
    }
}
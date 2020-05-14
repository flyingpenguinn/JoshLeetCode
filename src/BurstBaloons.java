import java.util.*;

/*
LC#312
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBaloons {
    // dplu is the max profit we can earn from l to u inclusive.
    // i is burst the last, not the first!!
    // note complexity is On^3 though n<=500
    int[][] dp;

    public int maxCoins(int[] a) {
        int n = a.length;
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dom(a, 0, a.length - 1);
    }

    int dom(int[] a, int l, int u) {
        if (l > u) {
            return 0;
        }
        if (dp[l][u] != -1) {
            return dp[l][u];
        }
        int max = 0;
        for (int i = l; i <= u; i++) {
            int cur = dom(a, l, i - 1) + dom(a, i + 1, u) + a[i] * c(a, l - 1) * c(a, u + 1);
            max = Math.max(max, cur);
        }
        dp[l][u] = max;
        return max;
    }

    int c(int[] a, int i) {
        if (i < 0 || i >= a.length) {
            return 1;
        }
        return a[i];
    }

    public static void main(String[] args) {
        int[] baloons2 = {3, 5};
        System.out.println(new BurstBaloonsIterative().maxCoins(baloons2));
    }
}

class BurstBaloonsIterative {
    // translation of the top down approach
    public int maxCoins(int[] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }
        int[][] dp = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                int max = 0;
                for (int k = i; k <= j; k++) {
                    int left = k == 0 ? 0 : dp[i][k - 1];
                    int right = k + 1 == n ? 0 : dp[k + 1][j];
                    int cur = left + right + a[k] * c(a, i - 1) * c(a, j + 1);
                    max = Math.max(max, cur);
                }
                dp[i][j] = max;
            }
        }
        return dp[0][n - 1];
    }

    int c(int[] a, int i) {
        if (i < 0 || i >= a.length) {
            return 1;
        }
        return a[i];
    }
}

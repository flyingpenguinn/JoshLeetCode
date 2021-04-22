import base.ArrayUtils;

import java.util.*;

/*
LC#1246
Given an integer array arr, in one move you can select a palindromic subarray arr[i], arr[i+1], ..., arr[j] where i <= j, and remove that subarray from the given array. Note that after removing a subarray, the elements on the left and on the right of that subarray move to fill the gap left by the removal.

Return the minimum number of moves needed to remove all numbers from the array.



Example 1:

Input: arr = [1,2]
Output: 2
Example 2:

Input: arr = [1,3,4,1,5]
Output: 3
Explanation: Remove [4] then remove [1,3,1] then remove [5].


Constraints:

1 <= arr.length <= 100
1 <= arr[i] <= 20
 */
public class PalindromeRemoval {
    // key observation: adding a pair of number outside of the array doesnt change the result
    private Integer[][] dp;

    public int minimumMoves(int[] a) {
        int n = a.length;
        dp = new Integer[n][n];
        return solve(a, 0, n - 1);
    }

    private int solve(int[] a, int i, int j) {
        if (i >= j) {
            return 1;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        if (a[i] == a[j]) {
            return solve(a, i + 1, j - 1);
            // can just extend an inner palin out
        }
        int min = Integer.MAX_VALUE;
        for (int k = j; k > i; k--) {
            if (a[j] == a[k]) {
                int left = solve(a, i, k - 1);
                int right = solve(a, k + 1, j - 1);
                int cur = left + right;
                min = Math.min(min, cur);
            }
        }
        dp[i][j] = min;
        return min;
    }

}
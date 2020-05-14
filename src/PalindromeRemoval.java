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
    public int minimumMoves(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i + 1 < n) {
                if (a[i] == a[i + 1]) {
                    dp[i][i + 1] = 1;
                } else {
                    dp[i][i + 1] = 2;
                }
            }
        }
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (a[i] == a[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    int min = n;
                    for (int k = i; k <= j - 1; k++) {
                        int cur = dp[i][k] + dp[k + 1][j];
                        min = Math.min(min, cur);
                    }
                    dp[i][j] = min;

                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        //     System.out.println(new PalindromeRemoval().minimumMoves(ArrayUtils.read1d("1,2")));
        System.out.println(new PalindromicRemovalAnotherDp().minimumMoves(ArrayUtils.read1d("[1,3,4,1,5]")));
        System.out.println(new PalindromeRemoval().minimumMoves(ArrayUtils.read1d("[16,13,13,10,12]")));
    }

}

class PalindromicRemovalAnotherDp {
    // alternative dp similar to remove boxes. only diff is we dont need p here
    int[][] dp;

    public int minimumMoves(int[] a) {
        int n = a.length;
        dp = new int[n + 1][n + 1];
        return dor(a, 0, n - 1);
    }
    //i...qxxxj
    int dor(int[] a, int i, int j) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int n = a.length;
        int min = dor(a, i, j - 1) + 1;
        int q = j - 1;
        while (q >= i) {
            //i...qkkkj
            if (a[q] == a[j]) {
                int qj = Math.max(1, dor(a, q + 1, j - 1));// at least 1
                int cur = dor(a, i, q - 1) + qj;
                // i,q and p..j together
                min = Math.min(min, cur);
            }
            q--;
        }
        dp[i][j] = min;
        return min;
    }

}
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
    Integer[][] dp;

    public int minimumMoves(int[] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }
        dp = new Integer[n][n];
        return domin(a, 0, a.length - 1);
    }

    private int domin(int[] a, int l, int u) {
        // invariant: we came from a non empty subarray from above so return 1 when empty means we found the "root" of a palindrome
        if (l >= u) {
            return 1;
        }
        if (dp[l][u] != null) {
            return dp[l][u];
        }
        int min = Integer.MAX_VALUE;
        for (int j = u; j >= l; j--) {
            if (a[j] == a[u]) {
                int left = l > j - 1 ? 0 : domin(a, l, j - 1); // need to check here because we don't know if left side is "naturally empty"
                int right = domin(a, j + 1, u - 1); // dont need the if check on the right because it's definitely not empty
                int cur = left + right;
                min = Math.min(min, cur);
            }
        }
        dp[l][u] = min;
        return min;
    }

}
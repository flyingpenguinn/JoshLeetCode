import java.util.Arrays;

/*
LC#960
We are given an array A of N lowercase letter strings, all of the same length.

Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.

For example, if we have an array A = ["babca","bbazb"] and deletion indices {0, 1, 4}, then the final array after deletions is ["bc","az"].

Suppose we chose a set of deletion indices D such that after deletions, the final array has every element (row) in lexicographic order.

For clarity, A[0] is in lexicographic order (ie. A[0][0] <= A[0][1] <= ... <= A[0][A[0].length - 1]), A[1] is in lexicographic order (ie. A[1][0] <= A[1][1] <= ... <= A[1][A[1].length - 1]), and so on.

Return the minimum possible value of D.length.



Example 1:

Input: ["babca","bbazb"]
Output: 3
Explanation: After deleting columns 0, 1, and 4, the final array is A = ["bc", "az"].
Both these rows are individually in lexicographic order (ie. A[0][0] <= A[0][1] and A[1][0] <= A[1][1]).
Note that A[0] > A[1] - the array A isn't necessarily in lexicographic order.
Example 2:

Input: ["edcba"]
Output: 4
Explanation: If we delete less than 4 columns, the only row won't be lexicographically sorted.
Example 3:

Input: ["ghi","def","abc"]
Output: 0
Explanation: All rows are already lexicographically sorted.


Note:

1 <= A.length <= 100
1 <= A[i].length <= 100
 */
public class DeleteColumnMakeSortedIII {
    // knapsack with an extra status of last kept column j. note whether we keep current column i is only relevent to j
    int[][] dp;

    public int minDeletionSize(String[] a) {
        int n = a[0].length();
        dp = new int[n + 1][n + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dom(a, 0, n);
    }

    int Max = 10000000;

    // min at i, last kept col is j. note j ==n means nothing before
    private int dom(String[] a, int i, int j) {
        int n = a[0].length();
        if (i == n) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int delete = 1 + dom(a, i + 1, j);
        boolean bad = false;
        if (j < n) {
            for (int k = 0; k < a.length; k++) {
                if (a[k].charAt(i) < a[k].charAt(j)) {
                    bad = true;
                    break;
                }
            }
        }
        int nodelete = bad == true ? Max : dom(a, i + 1, i);
        int rt = Math.min(delete, nodelete);
        dp[i][j] = rt;
        return rt;
    }
}

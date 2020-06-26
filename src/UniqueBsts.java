/*
LC#96
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */
public class UniqueBsts {
    int[] dp;

    public int numTrees(int n) {
        dp = new int[n + 1];
        return dfs(n);
    }

    private int dfs(int n) {
        if (n <= 1) {
            return 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        int r = 0;
        for (int left = 0; left <= n - 1; left++) {
            int lnum = dfs(left);
            int rnum = dfs(n - 1 - left);
            r += lnum * rnum;
        }
        dp[n] = r;
        return r;
    }
}

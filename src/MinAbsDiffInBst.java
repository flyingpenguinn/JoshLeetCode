import base.TreeNode;

/*
LC#530
Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).


Note: There are at least two nodes in this BST.
 */
public class MinAbsDiffInBst {
    // in order then find the diff. as it's sorted min diff must be between i and i+1
    Integer prev = null;
    int min = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }

    void dfs(TreeNode n) {
        if (n == null) {
            return;
        }
        dfs(n.left);
        if (prev != null) {
            min = Math.min(n.val - prev, min);
        }
        prev = n.val;
        dfs(n.right);
    }
}

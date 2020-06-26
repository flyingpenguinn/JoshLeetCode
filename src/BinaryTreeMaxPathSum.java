import base.TreeNode;

import java.util.HashMap;
import java.util.TreeSet;

/*
LC#124
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
 */
public class BinaryTreeMaxPathSum {
    public int maxPathSum(TreeNode n) {
        return dfs(n)[0];
    }

    // return: max path sum of this subtree, max sum of this node to a leaf
    private int[] dfs(TreeNode n) {
        if (n == null) {
            return new int[]{Integer.MIN_VALUE, 0};
        }
        int[] left = dfs(n.left);
        int[] right = dfs(n.right);
        int curpath = Math.max(n.val, Math.max(n.val + left[1], n.val + right[1]));
        int cursum = Math.max(curpath, n.val + left[1] + right[1]);
        int curmax = Math.max(left[0], Math.max(right[0], cursum));
        return new int[]{curmax, curpath};
    }
}

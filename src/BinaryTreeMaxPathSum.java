import base.TreeNode;

import java.util.HashMap;
import java.util.TreeSet;

import static java.lang.Math.max;

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
    class Solution {
        public int maxPathSum(TreeNode root) {
            return dfs(root)[0];
        }

        // max dist under this subtree, and max tree path sum from this node to some deeper nodes
        private int[] dfs(TreeNode n) {
            if (n == null) {
                return new int[]{Integer.MIN_VALUE, 0};
            }
            int[] left = dfs(n.left);
            int[] right = dfs(n.right);
            int curpath = max(n.val, max(n.val + left[1], n.val + right[1]));
            // when left1 and right1 are both neg, keep n.val is the best
            int maxchild = max(left[0], right[0]);
            int curmax = max(maxchild, max(curpath, n.val + left[1] + right[1]));
            return new int[]{curmax, curpath};
        }
    }
}

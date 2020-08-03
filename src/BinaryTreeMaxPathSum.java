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
    // trap:
    // 1. note the possibility that n.val is the max path itself, because both left[1] and right[1] are negative
    // 2. when null, the max sum under it must be -INF, not 0! there could be negative numbers
    // 3. what if the tree is empty?
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root)[0];
    }

    // 0: max path sum under this subtree, may cross this node, or any nodes under it
    // 1: max path from this node to certain node inside the subtree
    private int[] dfs(TreeNode n) {
        if (n == null) {
            return new int[]{Integer.MIN_VALUE, 0};
        }
        int[] left = dfs(n.left);
        int[] right = dfs(n.right);
        int maxPath = max(left[1] + n.val, right[1] + n.val, n.val);
        int maxCur = max(left[1] + right[1] + n.val, maxPath); // max path sum passing this node
        int maxSub = max(left[0], right[0], maxCur);
        return new int[]{maxSub, maxPath};
    }


    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    private int max(int a, int b) {
        return Math.max(a, b);
    }
}

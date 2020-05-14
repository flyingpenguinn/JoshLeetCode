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
    // return the pathed value, but record max in each node. what's returned is different from what's maxed
    public int maxPathSum(TreeNode root) {
        domax(root);
        return max;
    }

    int max = Integer.MIN_VALUE;

    private int domax(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = domax(root.left);
        int right = domax(root.right);
        int ml = left + root.val;
        int mr = right + root.val;
        int mall = right + left + root.val;
        int mroot = root.val;
        int rt = Math.max(ml, Math.max(mr, mroot));
        int cm = Math.max(mall, rt);
        max = Math.max(max, cm);
        return rt;
    }
}

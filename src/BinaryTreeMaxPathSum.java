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
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    // max sum of a path from n to some node below
    int dfs(TreeNode n){
        if(n==null){
            return 0;
        }
        int left = dfs(n.left);
        int right = dfs(n.right);
        int curpath = Math.max(left+n.val, Math.max(right+n.val, n.val));
        int curmax = Math.max(left+right+n.val, curpath);
        max = Math.max(curmax, max);
        return curpath;
    }
}

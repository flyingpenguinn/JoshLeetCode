import base.TreeNode;

/*
LC#687
Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

The length of path between two nodes is represented by the number of edges between them.



Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output: 2



Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output: 2



Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
public class LongestUniValuePath {
    // similar to #124 max path sum
    // count edges we just need left+right not left+right+1
    int r = 0;

    public int longestUnivaluePath(TreeNode root) {
        dol(root, null);
        return r;
    }

    int dol(TreeNode n, Integer pv) {
        if (n == null) {
            return 0;
        }
        int lv = dol(n.left, n.val);
        int rv = dol(n.right, n.val);
        int curres = lv + rv; // max we can get here with path crossing this node. edges, so left+right
        r = Math.max(r, curres);
        int max = Math.max(lv, rv);
        if (pv != null && n.val == pv) {
            return max + 1; // max we can return to the upper level
        } else {
            return 0;
        }
    }
}
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
    // count nodes so -1 in the end
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dol(root)[0] - 1;
    }

    int[] dol(TreeNode n) {
        if (n == null) {
            return new int[]{0, 0};
        }
        int[] lr = dol(n.left);
        int[] rr = dol(n.right);
        int mll = 1;
        int cm = 1;
        if (n.left != null && n.left.val == n.val) {
            mll += lr[1];
            cm += lr[1];
        }
        int mrl = 1;
        if (n.right != null && n.right.val == n.val) {
            mrl += rr[1];
            cm += rr[1];
        }
        int m0 = Math.max(lr[0], Math.max(cm, rr[0]));
        int m1 = Math.max(mrl, mll);
        //  System.out.println(n.val+" "+m0+" "+m1);
        return new int[]{m0, m1};
    }
}

class LongestUniPathWithPv {
    // another way: return max pv path starting at node n
    int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root, null);
        return max;
    }

    // max path len of given pv
    int dfs(TreeNode n, Integer pv) {
        if (n == null) {
            return 0;
        }

        int lh = dfs(n.left, n.val);
        int rh = dfs(n.right, n.val);
        int cl = lh + rh;
        max = Math.max(max, cl);
        if (pv != null && n.val == pv) {
            return Math.max(lh, rh) + 1;
        } else {
            return 0;
        }
    }
}
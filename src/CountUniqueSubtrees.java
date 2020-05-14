import base.TreeNode;

/*
LC#250
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4
 */
public class CountUniqueSubtrees {
    // force parent value down to check if syb agrees with it
    int r = 0;

    public int countUnivalSubtrees(TreeNode root) {
        dfs(root, null);
        return r;
    }

    // whether subtree is univalue and all equal to pv
    boolean dfs(TreeNode n, TreeNode p) {
        if (n == null) {
            return true;
        }

        boolean lr = dfs(n.left, n);
        boolean rr = dfs(n.right, n);
        if (lr && rr) {
            r++;
        }
        return p == null ? false : lr && rr && n.val == p.val;
    }

}

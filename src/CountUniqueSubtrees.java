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
    // recursive definition:
    // if my left and right are both uni value and i== them then i'm unit value too (or left or right is null)
    private int res = 0;

    public int countUnivalSubtrees(TreeNode root) {
        dfs(root);
        return res;
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean lr = dfs(root.left);
        boolean rr = dfs(root.right);
        if (lr && rr && (root.left == null || root.left.val == root.val) && (root.right == null || root.right.val == root.val)) {
            res++;
            return true;
        } else {
            return false;
        }
    }

}

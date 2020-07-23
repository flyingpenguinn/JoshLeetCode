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
    private int count = 0;

    public int countUnivalSubtrees(TreeNode root) {
        dfs(root);
        return count;
    }

    // whether n's subtree is uni value
    private boolean dfs(TreeNode n) {
        if (n == null) {
            return true;
        }
        boolean left = dfs(n.left);
        boolean right = dfs(n.right);
        if (left && right && (n.left == null || n.val == n.left.val)
                && (n.right == null || n.val == n.right.val)) {
            count++;
            return true;
        }
        return false;
    }

}

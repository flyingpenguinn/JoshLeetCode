import base.TreeNode;

/*
LC#404
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        return dos(root, null);
    }

    int dos(TreeNode root, TreeNode p) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null && p != null && p.left == root) {
            return root.val;
        }
        return dos(root.left, root) + dos(root.right, root);
    }
}

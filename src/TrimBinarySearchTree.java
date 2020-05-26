import base.TreeNode;

/*
LC#669
Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:
Input:
    1
   / \
  0   2

  L = 1
  R = 2

Output:
    1
      \
       2
Example 2:
Input:
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output:
      3
     /
   2
  /
 1
 */
public class TrimBinarySearchTree {
    public TreeNode trimBST(TreeNode root, int l, int r) {
        return dotrim(root, l, r);
    }

    // remaining subtree of n after trim
    TreeNode dotrim(TreeNode n, int l, int r) {
        if (n == null) {
            return null;
        }
        if (n.val < l) {
            return dotrim(n.right, l, r);
        } else if (n.val > r) {
            return dotrim(n.left, l, r);
        } else {
            TreeNode left = dotrim(n.left, l, r);
            TreeNode right = dotrim(n.right, l, r);
            n.left = left;
            n.right = right;
            return n;
        }
    }
}

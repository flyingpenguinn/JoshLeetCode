import base.TreeNode;

/*
Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
Trivia:
This problem was inspired by this original tweet by Max Howell:

Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode n) {
        return doi(n);
    }

    TreeNode doi(TreeNode n) {
        if (n == null) {
            return null;
        }
        TreeNode rt = n.right;
        n.right = doi(n.left);
        n.left = doi(rt);
        return n;
    }
}

import base.TreeNode;

/*
LC#111
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.
 */
public class MinDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dom(root);
    }

    int dom(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return dom(root.right) + 1;
        }
        if (root.right == null) {
            return dom(root.left) + 1;
        }
        return Math.min(dom(root.left), dom(root.right)) + 1;
    }
}

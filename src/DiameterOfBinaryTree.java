import base.TreeNode;

/*
LC#543
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfBinaryTree {
    // best result in this subtree and its longest to leaf path for upper level to use
    public int diameterOfBinaryTree(TreeNode root) {
        return dod(root)[0];
    }

    private int[] dod(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = dod(root.left);
        int[] right = dod(root.right);
        int ll = left[1];
        int rl = right[1];
        int curlong = ll + rl;
        int curmax = Math.max(left[0], Math.max(right[0], curlong));
        int curmaxpath = Math.max(ll + 1, rl + 1);
        return new int[]{curmax, curmaxpath};
    }
}

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
    // compare this to #124 max path sum: there the longest path may not be the max path
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    // longest to leaf path, node count
    int dfs(TreeNode n) {
        if (n == null) {
            return 0;
        }
        int left = dfs(n.left);
        int right = dfs(n.right);
        int curpath = Math.max(left + 1, right + 1);
        // want max edge count, so it's the sum of node counts
        max = Math.max(max, left + right);
        return curpath;
    }
}

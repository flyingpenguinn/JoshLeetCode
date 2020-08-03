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
    // diff: here the result for null is 0. we will never select "current node" as max, and note we return left+right as the max
    // value thouugh we are counting nodes
    public int diameterOfBinaryTree(TreeNode root) {

        return dfs(root)[0];
    }

    // max path under n subtree, and how max NODES from n to some leaf
    // note in 0, the max path is about edges but 1 is the nodes count
    private int[] dfs(TreeNode n) {
        if (n == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(n.left);
        int[] right = dfs(n.right);
        int maxPath = Math.max(left[1] + 1, right[1] + 1); // no need to compare with n/val
        int maxCur = Math.max(left[0], Math.max(right[0], right[1] + left[1])); // no need to compare with maxpath
        return new int[]{maxCur, maxPath};
    }
}

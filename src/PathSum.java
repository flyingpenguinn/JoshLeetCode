import base.TreeNode;

/*
LC#112
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class PathSum {
    // push value downward from parent to child
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, sum, 0);
    }

    boolean dfs(TreeNode root, int sum, int psum) {
        if (root == null) {
            return false;
        }
        int nc = psum + root.val;
        if (root.left == null && root.right == null) {
            return nc == sum;
        }
        return dfs(root.left, sum, nc) || dfs(root.right, sum, nc);
    }
}

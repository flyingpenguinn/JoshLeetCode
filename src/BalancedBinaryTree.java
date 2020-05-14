import base.TreeNode;

/*
LC#110
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.



Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Return false.
 */
public class BalancedBinaryTree {
    boolean balanced = true;

    public boolean isBalanced(TreeNode root) {
        dfs(root);
        return balanced;

    }

    int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        if (!balanced) {
            return -1;
        }
        int right = dfs(root.right);
        if (!balanced) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            balanced = false;
            return -1;
        } else {
            return Math.max(left, right) + 1;
        }
    }
}

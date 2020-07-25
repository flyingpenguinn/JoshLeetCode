import base.TreeNode;

/*
LC#98
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class ValidateBst {
    // could do post order too but it's way easier to push from root to children
    // validate if every node is good
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean dfs(TreeNode n, long min, long max) {
        if (n == null) {
            return true;
        }
        if (n.val < min || n.val > max) {
            return false;
        }
        // 1L for overflowing...
        return dfs(n.left, min, n.val - 1L) && dfs(n.right, n.val + 1L, max);
    }

}

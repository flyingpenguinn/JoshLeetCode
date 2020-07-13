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
    public boolean isValidBST(TreeNode root) {
        return doi(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean doi(TreeNode n, long l, long u) {
        if (n == null) {
            return true;
        }
        if (n.val < l || n.val > u) {
            return false;
        }
        return doi(n.left, l, n.val - 1L) && doi(n.right, n.val + 1L, u);
    }

}

class ValidateBstPostOrder {
    // slower as it needs to traverse the whole tree. but this approach can solve larget bst subtree problem
    public boolean isValidBST(TreeNode root) {
        return dfs(root)[2] == 1;
    }

    // min, max, isbst
    private long[] dfs(TreeNode n) {
        if (n == null) {
            return new long[]{Integer.MAX_VALUE + 1L, Integer.MIN_VALUE - 1L, 1};
        }
        long[] left = dfs(n.left);
        long[] right = dfs(n.right);
        long min = Math.min(n.val, Math.min(left[0], right[0]));
        long max = Math.max(n.val, Math.max(left[1], right[1]));
        if (n.val > left[1] && n.val < right[0] && left[2] == 1 && right[2] == 1) {
            return new long[]{min, max, 1};
        } else {
            return new long[]{min, max, 0};
        }
    }
}

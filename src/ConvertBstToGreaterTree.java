import base.TreeNode;

/*
LC#538
Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
Note: This question is the same as 1038: https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 */
public class ConvertBstToGreaterTree {
    int sum = 0;
    int runningsum = 0;

    public TreeNode convertBST(TreeNode root) {
        inorder(root, false);
        inorder(root, true);
        return root;
    }

    private void inorder(TreeNode root, boolean change) {
        if (root == null) {
            return;
        }
        inorder(root.left, change);
        if (!change) {
            sum += root.val;
        } else {
            runningsum += root.val;
            root.val += sum - runningsum;
        }
        inorder(root.right, change);
    }
}

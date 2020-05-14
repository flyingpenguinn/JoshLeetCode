import base.TreeNode;

/*
LC#333
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

Example:

Input: [10,5,15,1,8,null,7]

   10
   / \
  5  15
 / \   \
1   8   7

Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one.
             The return value is the subtree's size, which is 3.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
 */
public class LargestBSTSubTree {
    // post traverse. note here bst requires strict order, == is not allowed
    public int largestBSTSubtree(TreeNode root) {
        return dol(root)[4];
    }

    // size, min, max, isbst, maxbstsize
    int[] dol(TreeNode n) {
        if (n == null) {
            return new int[]{0, Integer.MAX_VALUE, Integer.MIN_VALUE, 1, 0};
        }
        int[] left = dol(n.left);
        int[] right = dol(n.right);
        int all = left[0] + right[0] + 1;
        int min = Math.min(n.val, Math.min(left[1], right[1]));
        int max = Math.max(n.val, Math.max(left[2], right[2]));
        if (left[3] == 1 && right[3] == 1 && left[2] < n.val && right[1] > n.val) {
            return new int[]{all, min, max, 1, all};
        } else {
            return new int[]{all, min, max, 0, Math.max(left[4], right[4])};
        }
    }
}

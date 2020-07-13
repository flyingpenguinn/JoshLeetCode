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
    // note here we can't use top down approach: that will only check if the whole tree is bst, not the subtrees!
    // to reckon subtree we have to do post order
    public int largestBSTSubtree(TreeNode root) {
        return dfs(root)[4];
    }

    // min, max, isbst, size, largest bst size
    private int[] dfs(TreeNode n){
        if(n==null){
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 1, 0, 0};
        }
        int[] left = dfs(n.left);
        int[] right = dfs(n.right);
        int min = Math.min(left[0], Math.min(right[0], n.val));
        int max = Math.max(left[1], Math.max(right[1], n.val));
        int isBst = 0;
        int maxBstSize = Math.max(left[4], right[4]);
        int count = 1+left[3]+right[3];
        if(left[2]==1 && right[2]==1 && n.val > left[1] && n.val <right[0]){
            isBst =1;
            maxBstSize = Math.max(maxBstSize, count);
        }
        return new int[]{min, max, isBst, count, maxBstSize};
    }
}

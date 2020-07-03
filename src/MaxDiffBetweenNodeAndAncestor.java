import base.TreeNode;

/*
LC#1026
Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

(A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)



Example 1:



Input: [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation:
We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.


Note:

The number of nodes in the tree is between 2 and 5000.
Each node will have value between 0 and 100000.
 */
public class MaxDiffBetweenNodeAndAncestor {
    public int maxAncestorDiff(TreeNode n) {
        // no int overflow in diffing
        // return -1 if not enough nodes
        if (n == null) {
            return -1;
        }
        return Math.max(dfs(n.left, n.val, n.val), dfs(n.right, n.val, n.val));
    }

    // min from ancestor, max from ancestor
    private int dfs(TreeNode n, int min, int max) {
        if (n == null) {
            return -1;
        }
        int left = dfs(n.left, Math.min(min, n.val), Math.max(max, n.val));
        int right = dfs(n.right, Math.min(min, n.val), Math.max(max, n.val));
        int curDiff = Math.max(Math.abs(min - n.val), Math.abs(max - n.val));
        return Math.max(curDiff, Math.max(left, right));
    }
}


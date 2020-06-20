import base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#938
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.



Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23


Note:

The number of nodes in the tree is at most 10000.
The final answer is guaranteed to be less than 2^31.
 */
public class RangeSumBst {
    public int rangeSumBST(TreeNode root, int l, int r) {
        return dfs(root, l, r);
    }

    int dfs(TreeNode n, int l, int r) {
        if (n == null) {
            return 0;
        }
        if (n.val < l) {
            // can't just write off this node: must check its right there could be bigger ones
            return dfs(n.right, l, r);
        }
        if (n.val > r) {
            return dfs(n.left, l, r);
        }
        return dfs(n.left, l, r) + dfs(n.right, l, r) + n.val;
    }
}

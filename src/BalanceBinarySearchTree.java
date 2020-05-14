import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
LC#1382
Given a binary search tree, return a balanced binary search tree with the same node values.

A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.

If there is more than one answer, return any of them.



Example 1:



Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.


Constraints:

The number of nodes in the tree is between 1 and 10^4.
The tree nodes will have distinct values between 1 and 10^5.
 */
public class BalanceBinarySearchTree {

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> allvalues = new ArrayList<>();
        dfs(root, allvalues);
        return balance(allvalues, 0, allvalues.size() - 1);
    }

    private TreeNode balance(List<Integer> allvalues, int l, int u) {
        if (l > u) {
            return null;
        }
        if (l == u) {
            return new TreeNode(allvalues.get(l));
        }
        int mid = l + (u - l) / 2;
        TreeNode left = balance(allvalues, l, mid - 1);
        TreeNode right = balance(allvalues, mid + 1, u);
        TreeNode root = new TreeNode(allvalues.get(mid));
        root.left = left;
        root.right = right;
        return root;

    }

    private void dfs(TreeNode root, List<Integer> allvalues) {
        if (root == null) {
            return;
        }
        dfs(root.left, allvalues);
        allvalues.add(root.val);
        dfs(root.right, allvalues);
    }
}

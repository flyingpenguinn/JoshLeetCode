import base.TreeNode;

/*
LC#1302
Given a binary tree, return the sum of values of its deepest leaves.


Example 1:



Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15


Constraints:

The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.
 */
public class DeepestLeavesSum {
    // one pass and O1 space
    int r = 0;
    int maxlevel = -1;

    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 0);
        return r;
    }

    private void dfs(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (i == maxlevel) {
                r += root.val;
            } else if (i > maxlevel) {
                maxlevel = i;
                r = root.val;
            }
        }
        dfs(root.left, i + 1);
        dfs(root.right, i + 1);
    }
}

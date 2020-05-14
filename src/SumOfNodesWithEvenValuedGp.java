/*
LC#1315
Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)

If there are no nodes with an even-valued grandparent, return 0.



Example 1:



Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.


Constraints:

The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.
 */

import base.TreeNode;

public class SumOfNodesWithEvenValuedGp {
    int r = 0;

    public int sumEvenGrandparent(TreeNode root) {
        dfs(root, null, null);
        return r;
    }

    private void dfs(TreeNode n, TreeNode gp, TreeNode p) {
        if (n == null) {
            return;
        }
        if (gp != null && gp.val % 2 == 0) {
            r += n.val;
        }
        dfs(n.left, p, n);
        dfs(n.right, p, n);
    }
}

import base.TreeNode;

/*
LC#671
Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:

Input:
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.


Example 2:

Input:
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.
 */
public class SecondMinNodeInBst {
    // we must dfs because there could be equal nodes with min value
    long Max = Integer.MAX_VALUE + 1L;
    ;

    public int findSecondMinimumValue(TreeNode n) {
        long l = dof(n.left, n.val);
        long r = dof(n.right, n.val);
        long min = Math.min(l, r);
        if (min >= Max) {
            return -1;
        }
        return (int) min;
    }

    // get min excluding exc. only dfs when we hit exc
    long dof(TreeNode n, int exc) {
        if (n == null) {
            return Max;
        }
        if (n.val != exc) {
            return n.val;
        }
        return Math.min(dof(n.left, exc), dof(n.right, exc));
    }

}

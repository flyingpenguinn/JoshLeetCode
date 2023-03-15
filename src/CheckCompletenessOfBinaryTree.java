import base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/*
LC#958
Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.



Example 1:



Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
Example 2:



Input: [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.

Note:

The tree will have between 1 and 100 nodes.
 */
public class CheckCompletenessOfBinaryTree {
    // complete binary tree = if we index the tree it fully occupies 1 to n without null
    private int nodes = 0;

    public boolean isCompleteTree(TreeNode root) {
        nodes = count(root);
        return dfs(root, 1);
    }

    private int count(TreeNode n) {
        return n == null ? 0 : 1 + count(n.left) + count(n.right);
    }

    private boolean dfs(TreeNode n, int cur) {
        if (n == null) {
            return cur > nodes;
        }
        if (cur > nodes) {
            return false;
        }
        return dfs(n.left, 2 * cur) && dfs(n.right, 2 * cur + 1);
    }
}

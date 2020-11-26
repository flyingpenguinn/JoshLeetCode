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
    // dfs way!  null is either on h or h-1. once we met a null on h-1 we know the last row is done. shouldnt see null there. can only see null on h-1 after that
    private boolean alllast = false;
    // met all last level nodes. note we meet every level left to right
    private int h = 0;

    public boolean isCompleteTree(TreeNode root) {
        return dfs(root, 1);
    }

    private boolean dfs(TreeNode n, int level) {

        if (n == null) {
            if (h == 0) {
                h = level;
            } else if (level < h) {
                alllast = true;
                // we should have seen all null level nodes
            }
            return level == h - (alllast ? 1 : 0);
// if we havent seen all null level then h, otherwise h-1
        }
        return dfs(n.left, level + 1) && dfs(n.right, level + 1);
    }
}

class CheckCompletenessCounter {
    // once we see null it's all null afterwards
    public boolean isCompleteTree(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean seen = false;
        while (!q.isEmpty()) {
            TreeNode top = q.poll();
            if (top == null) {
                seen = true;
            } else {
                if (seen) {
                    return false;
                }
                q.offer(top.left);
                q.offer(top.right);
            }
        }
        return true;
    }
}

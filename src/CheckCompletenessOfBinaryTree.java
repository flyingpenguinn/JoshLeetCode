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
    // when we see a null next generation, no more nodes with next generation.
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        boolean seen = false;
        while (!q.isEmpty()) {
            TreeNode top = q.poll();
            if (top.left == null) {
                seen = true;
            } else {
                if (seen) {
                    return false;
                }
                q.offer(top.left);
            }
            if (top.right == null) {
                seen = true;
            } else {
                if (seen) {
                    return false;
                }
                q.offer(top.right);
            }
        }
        return true;
    }
}

class CheckCompletenessCounter {
    // complete tree has nodes at index 2*i+1 and 2*i+2. verify if we see this
    private class Result {
        private TreeNode n;
        private int c;

        public Result(TreeNode n, int c) {
            this.n = n;
            this.c = c;
        }
    }

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<Result> q = new ArrayDeque<>();
        q.offer(new Result(root, 0));
        int count = 0;
        while (!q.isEmpty()) {
            Result top = q.poll();
            if (top.c != count) {
                return false;
            }
            if (top.n.left != null) {
                q.offer(new Result(top.n.left, 2 * top.c + 1));
            }
            if (top.n.right != null) {
                q.offer(new Result(top.n.right, 2 * top.c + 2));
            }
            count++;
        }
        return true;
    }
}

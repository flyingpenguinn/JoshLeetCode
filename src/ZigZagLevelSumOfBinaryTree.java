import base.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ZigZagLevelSumOfBinaryTree {
    public List<Long> zigzagLevelSum(TreeNode root) {
        List<Long> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        Deque<TreeNode> next = new ArrayDeque<>();
        q.offerLast(root);
        int rem = 1;
        int cl = 1;
        while (!q.isEmpty()) {
            long clevel = 0;
            boolean count = true;
            while (!q.isEmpty()) {
                TreeNode top = null;
                top = q.pollLast();
                if (cl % 2 == 1) {
                    if (top.left == null) {
                        count = false;
                    }
                } else {
                    if (top.right == null) {
                        count = false;
                    }
                }
                if (count) {
                    clevel += top.val;
                }
                if (cl % 2 == 1) {
                    if (top.left != null) {
                        next.offerLast(top.left);
                    }
                    if (top.right != null) {
                        next.offerLast(top.right);
                    }
                } else {
                    if (top.right != null) {
                        next.offerLast(top.right);
                    }
                    if (top.left != null) {
                        next.offerLast(top.left);
                    }
                }

            }
            res.add(clevel);
            q = next;
            next = new ArrayDeque<>();
            ++cl;
        }
        return res;
    }
}

import base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvenOddTree {
    private int min = -1;
    private int max = 1000000000;

    public boolean isEvenOddTree(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        int curcount = 1;
        q.offer(root);
        int level = 0;
        int last = min;
        while (!q.isEmpty()) {
            TreeNode top = q.poll();
            if (level % 2 == 0) {
                if (top.val % 2 == 0 || top.val <= last) {
                    return false;
                }
            } else {
                if (top.val % 2 == 1 || top.val >= last) {
                    return false;
                }
            }
            if (top.left != null) {
                q.offer(top.left);
            }
            if (top.right != null) {
                q.offer(top.right);
            }
            curcount--;
            last = top.val;
            if (curcount == 0) {
                curcount = q.size();
                level++;
                if (level % 2 == 0) {
                    last = min;
                } else {
                    last = max;
                }
            }
        }
        return true;
    }
}

import base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class TwoSumInputBst {

    // best: Olngn in space complexity, On in time
    public boolean findTarget(TreeNode root, int k) {
        Deque<TreeNode> ls = new ArrayDeque<>();
        Deque<TreeNode> rs = new ArrayDeque<>();
        keepPush(root, ls, true);
        keepPush(root, rs, false);

        while (!ls.isEmpty() && !rs.isEmpty()) {
            TreeNode ltop = ls.peek();
            TreeNode rtop = rs.peek();
            if (ltop == rtop) {
                return false;
            }
            int sum = ltop.val + rtop.val;
            if (sum == k) {
                return true;
            } else if (sum < k) {
                keepPush(ls.pop().right, ls, true);
            } else {
                keepPush(rs.pop().left, rs, false);
            }
        }
        return false;
    }

    void keepPush(TreeNode p, Deque<TreeNode> stack, boolean left) {
        while (p != null) {
            stack.push(p);
            if (left) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
    }
}

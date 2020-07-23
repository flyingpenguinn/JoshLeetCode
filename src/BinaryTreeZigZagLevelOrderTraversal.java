import base.TreeNode;

import java.util.*;

public class BinaryTreeZigZagLevelOrderTraversal {
    // cant change inqueue order can only change the seq of adding to the list: even if we add right first, next round we can't get
    // left's left before right's left
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>();
        if (root == null) {
            return r;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int curlen = 1;
        boolean reverse = false;
        LinkedList<Integer> curlevel = new LinkedList<>();
        while (!q.isEmpty()) {
            TreeNode top = q.poll();
            if (reverse) {
                curlevel.addFirst(top.val);
            } else {
                curlevel.add(top.val);
            }
            curlen--;
            if (top.left != null) {
                q.offer(top.left);
            }
            if (top.right != null) {
                q.offer(top.right);
            }

            if (curlen == 0) {
                r.add(curlevel);
                curlevel = new LinkedList<>();
                curlen = q.size();
                reverse = !reverse;
            }
        }
        return r;
    }
}

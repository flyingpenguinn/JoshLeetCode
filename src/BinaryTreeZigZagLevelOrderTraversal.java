import base.TreeNode;

import java.util.*;

public class BinaryTreeZigZagLevelOrderTraversal {
    // cant change inqueue order
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        LinkedList<Integer> curLevel = new LinkedList<>();
        int level = 1;
        boolean left = true;
        while (!q.isEmpty()) {
            TreeNode top = q.poll();
            if (left) {
                curLevel.addLast(top.val);
            } else {
                curLevel.addFirst(top.val);
            }
            if (top.left != null) {
                q.offer(top.left);
            }
            if (top.right != null) {
                q.offer(top.right);
            }
            level--;
            if (level == 0) {
                result.add(curLevel);
                curLevel = new LinkedList<>();
                level = q.size();
                left = !left;
            }
        }

        return result;
    }
}

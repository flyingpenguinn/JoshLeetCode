import base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class HeightOfSpecialBinaryTree {
    public int heightOfTree(TreeNode root) {
        Deque<TreeNode> dq = new ArrayDeque<>();
        Map<TreeNode, Integer> dist = new HashMap<>();
        dq.offer(root);
        dist.put(root, 0);
        int res = 0;
        while (!dq.isEmpty()) {
            TreeNode top = dq.poll();
            int cd = dist.get(top);
            //  System.out.println(top.val+" "+cd);
            res = Math.max(res, cd);
            TreeNode left = top.left;

            if (left != null && left.right != top && !dist.containsKey(left)) {
                dist.put(left, cd + 1);
                dq.offer(left);
            }
            TreeNode right = top.right;
            if (right != null && right.left != top && !dist.containsKey(right)) {
                dist.put(right, cd + 1);
                dq.offer(right);
            }
        }
        return res;
    }
}

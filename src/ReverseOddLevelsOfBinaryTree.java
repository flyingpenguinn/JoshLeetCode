import base.TreeNode;

import java.util.*;

public class ReverseOddLevelsOfBinaryTree {
    public TreeNode reverseOddLevels(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        List<TreeNode> last = new ArrayList<>();
        List<TreeNode> cur = new ArrayList<>();
        int ct = 1;
        int cl = 0;
        while (!q.isEmpty()) {
            TreeNode top = q.poll();
            --ct;
            cur.add(top);
            if (top.left != null) {
                q.offer(top.left);
            }
            if (top.right != null) {
                q.offer(top.right);
            }
            if (ct == 0) {
                int j = 0;
                if (cl % 2 == 1) {
                    Collections.reverse(cur);
                }
                for (int i = 0; i < last.size(); ++i) {
                    last.get(i).left = cur.get(j++);
                    last.get(i).right = cur.get(j++);
                }
                last = cur;
                cur = new ArrayList<>();
                ct = q.size();
                ++cl;
            }
        }
        return root;
    }
}

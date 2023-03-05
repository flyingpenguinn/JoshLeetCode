import base.TreeNode;

import java.util.*;

public class KthLargestSumInBinaryTree {
    public long kthLargestLevelSum(TreeNode root, int k) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int curlen = 1;
        List<Long> levelsums = new ArrayList<>();
        long csum = 0;
        while (!q.isEmpty()) {
            TreeNode top = q.poll();
            curlen--;
            csum += top.val;
            if (top.left != null) {
                q.offer(top.left);
            }
            if (top.right != null) {
                q.offer(top.right);
            }
            if (curlen == 0) {
                curlen = q.size();
                levelsums.add(csum);
                csum = 0;
            }
        }
        if (levelsums.size() < k) {
            return -1;
        }

        Collections.sort(levelsums);

        //  System.out.println(levelsums);
        return levelsums.get(levelsums.size() - k);
    }
}

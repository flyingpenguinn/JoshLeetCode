import base.TreeNode;

import java.util.LinkedList;

public class CheckCompletenessOfBinaryTree {
    // bfs once we see null we can't see anything else
    // include null into the bfs
    public boolean isCompleteTree(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean nullseen = false;
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n == null) {
                if (!nullseen) {
                    nullseen = true;
                }
            } else {
                if (nullseen) {
                    return false;
                }
                q.offer(n.left);
                q.offer(n.right);
            }
        }
        return true;
    }
}

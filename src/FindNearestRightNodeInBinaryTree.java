import base.TreeNode;

public class FindNearestRightNodeInBinaryTree {
    // can also do a bfs
    private int ud = -1;
    private TreeNode res = null;

    public TreeNode findNeartestRightNode(TreeNode root, TreeNode u) {
        dfs(root, u, 0);
        return res;
    }

    private void dfs(TreeNode n, TreeNode u, int d) {
        if (n == null || res != null) {
            return;
        }
        if (n == u) {
            ud = d;
            return;
        }
        if (d == ud) {
            res = n;
            return;
        } else if (ud == -1 || d < ud) {
            dfs(n.left, u, d + 1);
            dfs(n.right, u, d + 1);
        }
    }
}

import base.TreeNode;

public class CousinsInBinaryTree {
    // use class variable to only do traverse once
    int xd;
    int yd;

    TreeNode xp = null;
    TreeNode yp = null;

    int xv;
    int yv;

    public boolean isCousins(TreeNode root, int x, int y) {
        xv = x;
        yv = y;
        dfs(root, null, 0);
        return xd == yd && xp != yp;
    }

    void dfs(TreeNode node, TreeNode p, int d) {
        if (node == null) {
            return;
        }
        if (node.val == xv) {
            xd = d;
            xp = p;
        }
        if (node.val == yv) {
            yd = d;
            yp = p;
        }
        dfs(node.left, node, d + 1);
        dfs(node.right, node, d + 1);

    }

}

import base.TreeNode;

public class CountDominantNodesInBinaryTree {
    private int res = 0;

    public int countDominantNodes(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode n) {
        if (n == null) {
            return 0;
        }
        int lr = dfs(n.left);
        int rr = dfs(n.right);
        int cres = Math.max(n.val, Math.max(lr, rr));
        if (n.val == cres) {
            ++res;
        }
        return cres;
    }
}

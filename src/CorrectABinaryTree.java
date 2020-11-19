import base.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class CorrectABinaryTree {
    private Set<TreeNode> seen = new HashSet<>();

    public TreeNode correctBinaryTree(TreeNode root) {
        dfs(root, null);
        return root;
    }

    private void dfs(TreeNode n, TreeNode p) {
        if (n == null) {
            return;
        }
        if (seen.contains(n.right)) {
            if (n == p.left) {
                p.left = null;
            } else {
                p.right = null;
            }
            return;
        }
        seen.add(n);
        dfs(n.right, n);
        dfs(n.left, n);
    }
}

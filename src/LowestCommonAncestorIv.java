import base.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorIv {
    private TreeNode lca = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<TreeNode> set = new HashSet<>();
        for (TreeNode n : nodes) {
            set.add(n);
        }
        dfs(root, set);
        return lca;
    }

    private int dfs(TreeNode n, Set<TreeNode> nodes) {
        if (lca != null) {
            return -1;
        }
        if (n == null) {
            return 0;
        }
        int lr = dfs(n.left, nodes);
        int rr = dfs(n.right, nodes);
        int curc = 0;
        if (nodes.contains(n)) {
            curc++;
        }
        int rt = lr + rr + curc;
        if (rt == nodes.size() && lca == null) {
            lca = n;
        }
        return rt;
    }
}

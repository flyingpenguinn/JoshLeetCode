import base.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorOfBinaryTreeIv {

    // if guaranteed exist, we have a simpler way: return the node if it's part of the set
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<TreeNode> set = new HashSet<>();
        for (TreeNode n : nodes) {
            set.add(n);
        }
        return dfs(root, set);
    }

    private TreeNode dfs(TreeNode n, Set<TreeNode> nodes) {

        if (n == null) {
            return null;
        }
        if (nodes.contains(n)) {
            // all unique, later ones can't beat n
            return n;
        }
        TreeNode lr = dfs(n.left, nodes);
        TreeNode rr = dfs(n.right, nodes);
        // n unites the two camps
        if (lr != null && rr != null) {
            return n;
        } else {
            return lr != null ? lr : rr;
        }
    }
}

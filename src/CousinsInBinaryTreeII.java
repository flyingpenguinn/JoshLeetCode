import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class CousinsInBinaryTreeII {
    private Map<Integer, Integer> lm = new HashMap<>();

    public TreeNode replaceValueInTree(TreeNode root) {
        dfs(root, 0);
        dfs2(root, null, 0);
        return root;
    }

    private void dfs(TreeNode node, int dep) {
        if (node == null) {
            return;
        }
        int cur = lm.getOrDefault(dep, 0) + node.val;
        lm.put(dep, cur);
        dfs(node.left, dep + 1);
        dfs(node.right, dep + 1);
    }

    private void dfs2(TreeNode node, TreeNode p, int dep) {
        if (node == null) {
            return;
        }
        if (p == null) {
            node.val = 0;
        }
        int olv = 0;
        if (node.left != null) {
            int sum = lm.get(dep + 1);
            sum -= node.left.val;
            if (node.right != null) {
                sum -= node.right.val;
            }
            olv = node.left.val;
            node.left.val = sum;
        }
        if (node.right != null) {
            int sum = lm.get(dep + 1);
            sum -= node.right.val;
            if (node.left != null) {
                sum -= olv;
            }
            node.right.val = sum;
        }
        dfs2(node.left, node, dep + 1);
        dfs2(node.right, node, dep + 1);
    }
}

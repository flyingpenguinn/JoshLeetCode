import base.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CreateBinaryTreeFromDescriptions {
    private Set<Integer> nodes = new HashSet<>();
    private Set<Integer> children = new HashSet<>();
    private Map<Integer, TreeNode> dp = new HashMap<>();

    private TreeNode getTreeNode(int n) {
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        TreeNode nn = new TreeNode(n);
        dp.put(n, nn);
        return nn;
    }

    public TreeNode createBinaryTree(int[][] descs) {
        for (int[] d : descs) {
            TreeNode cur = getTreeNode(d[0]);
            TreeNode kid = getTreeNode(d[1]);
            if (d[2] == 1) {
                cur.left = kid;
            } else {
                cur.right = kid;
            }
            dp.put(d[0], cur);
            nodes.add(d[0]);
            children.add(d[1]);
        }
        int root = -1;
        for (int n : nodes) {
            if (!children.contains(n)) {
                root = n;
                break;
            }
        }
        return dp.get(root);
    }
}

import base.TreeNode;
import base.Trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodesReturnForest {

    public List<TreeNode> delNodes(TreeNode root, int[] d) {
        Set<Integer> tod = new HashSet<>();
        for (int di : d) {
            tod.add(di);
        }
        return new ArrayList<>(dfs(root, tod, null));

    }

    private List<TreeNode> dfs(TreeNode root, Set<Integer> tod, TreeNode parent) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<TreeNode> left = dfs(root.left, tod, root);
        List<TreeNode> right = dfs(root.right, tod, root);
        left.addAll(right);
        if (tod.contains(root.val)) {
            removeNode(root, parent);
        } else {
            if (parent == null || tod.contains(parent.val)) {
                // no parent coverage...and itself isn't deleted, add to result
                left.add(root);
            }
        }
        return left;
    }

    private void removeNode(TreeNode root, TreeNode parent) {
        if (parent != null && root == parent.left) {
            parent.left = null;
        } else if (parent != null) {
            parent.right = null;
        }
        root.left = null;
        root.right = null;
    }

    public static void main(String[] args) {
        int[] d = {3, 5};
        List<TreeNode> trees = (new DeleteNodesReturnForest().delNodes(Trees.fromString("1,2,3,4,5,6,7"), d));
        for (TreeNode t : trees) {
            System.out.println(Trees.toString(t));
        }
    }
}

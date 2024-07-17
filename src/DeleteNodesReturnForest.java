import base.TreeNode;
import base.Trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodesReturnForest {

    private HashSet<Integer> set = new HashSet<>();
    private List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] del) {
        for (int ti : del) {
            set.add(ti);
        }
        dfs(root, null);
        return res;
    }

    private void dfs(TreeNode n, TreeNode parent) {
        if (n == null) {
            return;
        }
        // trap: deal with current node being deleted
        // trap: deal with root being deleted
        if ((parent == null || set.contains(parent.val)) && !set.contains(n.val)) {
            res.add(n);
        }
        TreeNode olt = n.left;
        TreeNode ort = n.right;
        if (olt != null && set.contains(olt.val)) {
            n.left = null;
        }
        if (ort != null && set.contains(ort.val)) {
            n.right = null;
        }
        dfs(olt, n);
        dfs(ort, n);
    }

    public static void main(String[] args) {
        int[] d = {3, 5};
        List<TreeNode> trees = (new DeleteNodesReturnForest().delNodes(Trees.fromString("1,2,3,4,5,6,7"), d));
        for (TreeNode t : trees) {
            System.out.println(Trees.toString(t));
        }
    }
}

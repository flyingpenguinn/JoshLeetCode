import base.TreeNode;

public class InsertIntoBst {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);

        }
        doInsert(root, val);
        return root;

    }

    private void doInsert(TreeNode node, int val) {
        if (node.val < val) {
            if (node.right == null) {
                node.right = new TreeNode(val);
            } else {
                doInsert(node.right, val);
            }
        } else {
            if (node.left == null) {
                node.left = new TreeNode(val);
            } else {
                doInsert(node.left, val);
            }
        }
    }
}

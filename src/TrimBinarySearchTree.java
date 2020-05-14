import base.TreeNode;

public class TrimBinarySearchTree {
    public TreeNode trimBST(TreeNode root, int l, int r) {
        return dotrim(root, l, r);
    }

    TreeNode dotrim(TreeNode node, int l, int r) {
        if (node == null) {
            return null;
        }
        if (node.val < l) {
            return dotrim(node.right, l, r);
        } else if (node.val > r) {
            return dotrim(node.left, l, r);
        } else {
            TreeNode lft = dotrim(node.left, l, r);
            TreeNode rgt = dotrim(node.right, l, r);
            node.left = lft;
            node.right = rgt;
            return node;
        }
    }
}

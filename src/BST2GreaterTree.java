import base.TreeNode;

public class BST2GreaterTree {
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        int oldval = root.val;
        root.val += sum;
        sum += oldval;
        convertBST(root.left);
        return root;

    }
}

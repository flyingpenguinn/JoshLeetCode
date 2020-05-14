import base.TreeNode;

public class BstToGreaterSumTree {
    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        getSum(root);
        return root;
    }

    private void getSum(TreeNode node) {
        if (node == null) {
            return;
        }
        getSum(node.right);
        sum += node.val;
        node.val = sum;
        getSum(node.left);

    }
}

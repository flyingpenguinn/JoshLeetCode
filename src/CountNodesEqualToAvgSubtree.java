import base.TreeNode;

public class CountNodesEqualToAvgSubtree {
    private int res = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return res;
    }

    // sum, n
    private int[] dfs(TreeNode n) {
        if (n == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(n.left);
        int[] right = dfs(n.right);
        int sum = left[0] + right[0] + n.val;
        int count = left[1] + right[1] + 1;
        if (n.val == sum / count) {
            ++res;
        }
        return new int[]{sum, count};
    }
}

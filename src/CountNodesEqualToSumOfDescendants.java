import base.TreeNode;

public class CountNodesEqualToSumOfDescendants {
    private int count = 0;
    public int equalToDescendants(TreeNode root) {
        dfs(root);
        return count;
    }

    private int dfs(TreeNode n){
        if(n==null){
            return 0;
        }
        int left = dfs(n.left);
        int right = dfs(n.right);
        if(n.val == left+right){
            ++count;
        }
        return left+right+n.val;
    }
}

import base.TreeNode;

public class SmallestSubtreeWithDeepestNodes {
    // exact same problem as lca of deepest nodes...
    class Result {
        TreeNode lca;
        int depth;

        public Result(TreeNode lca, int depth) {
            this.lca = lca;
            this.depth = depth;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).lca;
    }

    private Result dfs(TreeNode n) {
        if (n == null) {
            return new Result(null, 0);
        }
        Result left = dfs(n.left);
        Result right = dfs(n.right);
        if (left.depth == right.depth) {
            return new Result(n, left.depth + 1);
        } else if (left.depth > right.depth) {
            return new Result(left.lca, left.depth + 1);
        } else {
            return new Result(right.lca, right.depth + 1);
        }
    }
}

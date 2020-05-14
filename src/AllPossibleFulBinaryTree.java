import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class AllPossibleFulBinaryTree {

    // not deep copy but fine in this problem because all trees at n  are identical...
    List<TreeNode>[] dp;

    public List<TreeNode> allPossibleFBT(int n) {
        dp = new ArrayList[n + 1];
        return doall(n);
    }

    List<TreeNode> doall(int n) {
        List<TreeNode> r = new ArrayList<>();
        if (n == 1) {
            r.add(new TreeNode(0));
        } else {
            if (dp[n] != null) {
                return dp[n];
            }
            for (int i = 1; i < n - 1; i++) {
                List<TreeNode> left = doall(i);
                List<TreeNode> right = doall(n - 1 - i);
                for (TreeNode le : left) {
                    for (TreeNode ri : right) {
                        TreeNode root = new TreeNode(0);
                        root.left = le;
                        root.right = ri;
                        r.add(root);
                    }
                }
            }
        }
        dp[n] = r;
        return r;
    }
}

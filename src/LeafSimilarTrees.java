import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
LC#872
Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.



For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.



Note:

Both of the given trees will have between 1 and 100 nodes.
 */
public class LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = dfs(root1);
        List<Integer> l2 = dfs(root2);
        return l1.equals(l2);
    }

    List<Integer> dfs(TreeNode n) {
        List<Integer> r = new ArrayList<>();
        if (n == null) {
            return r;
        }
        if (n.left == null && n.right == null) {
            r.add(n.val);
            return r;
        }
        List<Integer> left = dfs(n.left);
        List<Integer> right = dfs(n.right);
        left.addAll(right);
        return left;
    }
}

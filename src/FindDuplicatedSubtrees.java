import base.TreeNode;

import java.util.*;

/*
LC#652
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:

      2
     /
    4
and

    4
Therefore, you need to return above trees' root in the form of a list.
 */
public class FindDuplicatedSubtrees {
    // serialize a tree then use hashmap to look up
    Map<String, Integer> m = new HashMap<>();
    List<TreeNode> r = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode n) {
        dfs(n);
        return new ArrayList<>(r);

    }

    String dfs(TreeNode n) {
        if (n == null) {
            return "";
        }
        String lr = dfs(n.left);
        String rr = dfs(n.right);
        String cur = n.val + "," + lr + "," + rr;
        if (m.getOrDefault(cur, 0) == 1) {
            r.add(n);
        }
        m.put(cur, m.getOrDefault(cur, 0) + 1);
        return cur;
    }
}

import base.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC#199
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
 */
public class BinaryTreeRightsideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        dfs(root, 0, r);
        return r;
    }

    private void dfs(TreeNode n, int l, List<Integer> r) {
        if (n == null) {
            return;
        }
        List<Integer> list = null;
        if (r.size() == l) {
            r.add(n.val);
        } else {
            r.set(l, n.val);
        }
        dfs(n.left, l + 1, r);
        dfs(n.right, l + 1, r);
    }
}

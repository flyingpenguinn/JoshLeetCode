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
        List<Integer> list = new ArrayList<>();
        dfs(root, list, 0);
        return list;
    }

    private void dfs(TreeNode n, List<Integer> list, int depth) {
        if (n == null) {
            return;
        }
        if (depth == list.size()) {
            list.add(n.val);
        } else {
            list.set(depth, n.val);
        }
        dfs(n.left, list, depth + 1);
        dfs(n.right, list, depth + 1);
    }
}

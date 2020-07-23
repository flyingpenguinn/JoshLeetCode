import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
LC#257
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        dfs(root, paths, new StringBuilder(""));
        return paths;
    }

    // node non null
    private void dfs(TreeNode node, List<String> paths, StringBuilder cur) {
        int oldSize = cur.length();
        if (cur.length() > 0) {
            cur.append("->");
        }
        cur.append(node.val);
        if (node.left == null && node.right == null) {
            paths.add(cur.toString());
        }
        if (node.left != null) {
            dfs(node.left, paths, cur);
        }
        if (node.right != null) {
            dfs(node.right, paths, cur);
        }
        cur.setLength(oldSize); // ! good approach
    }
}

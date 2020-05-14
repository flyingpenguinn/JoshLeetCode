import base.TreeNode;

/*
LC#513
Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2:
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.
 */
public class FindBottomLeftTreeValue {
    // dfs can ensure left to right, but can't ensure height
    int r = 0;
    int maxdepth = -1;

    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);
        return r;
    }

    void dfs(TreeNode n, int depth) {
        if (n == null) {
            return;
        }
        if (depth > maxdepth) {
            r = n.val;
            maxdepth = depth;
        }
        dfs(n.left, depth + 1);
        dfs(n.right, depth + 1);
    }
}


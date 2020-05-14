import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
LC#113
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
 */
public class PathSumII {
    List<List<Integer>> r = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum, 0, new ArrayList<>());
        return r;
    }

    void dfs(TreeNode root, int sum, int psum, List<Integer> plist) {
        if (root == null) {
            return;
        }
        int nc = psum + root.val;
        plist.add(root.val);
        if (root.left == null && root.right == null && nc == sum) {
            r.add(new ArrayList<>(plist)); // must copy result in backtracking
        }
        dfs(root.left, sum, nc, plist);
        dfs(root.right, sum, nc, plist);
        plist.remove(plist.size() - 1);
    }
}

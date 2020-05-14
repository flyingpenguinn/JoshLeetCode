import base.TreeNode;

import java.util.*;

/*
LC#102
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 */

public class BinaryTreeLevelOrderTraversal {
    // dfs can guarantee left to right but not top to bottom so can't apply this to vertical order
    // dont need a map
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>();
        dfs(root, 0, r);
        return r;
    }

    private void dfs(TreeNode root, int level, List<List<Integer>> r) {
        if (root == null) {
            return;
        }
        List<Integer> list = null;
        if (r.size() == level) {
            list = new ArrayList<>();
            r.add(list);
        } else {
            list = r.get(level);
        }
        list.add(root.val);
        dfs(root.left, level + 1, r);
        dfs(root.right, level + 1, r);
    }
}

class LevelOrderBfs {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        List<Integer> curLevel = new ArrayList<>();
        int level = 1;
        while (!q.isEmpty()) {
            TreeNode top = q.poll();
            curLevel.add(top.val);
            if (top.left != null) {
                q.offer(top.left);
            }
            if (top.right != null) {
                q.offer(top.right);
            }
            level--;
            if (level == 0) {
                result.add(curLevel);
                curLevel = new ArrayList<>();
                level = q.size();
            }
        }
        return result;
    }
}

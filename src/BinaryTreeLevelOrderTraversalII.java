import base.TreeNode;

import java.util.*;

/*
LC#107
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
 */
public class BinaryTreeLevelOrderTraversalII {
    // each level concat to the head of a list
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        LinkedList<List<Integer>> r = new LinkedList<>();
        if (root == null) {
            return r;
        }
        q.offer(root);
        List<Integer> ll = new ArrayList<>();
        int level = 1;
        while (!q.isEmpty()) {
            TreeNode top = q.poll();

            ll.add(top.val);
            if (top.left != null) {
                q.offer(top.left);
            }
            if (top.right != null) {
                q.offer(top.right);
            }
            level--;
            if (level == 0) {
                level = q.size();
                r.addFirst(ll); // rev here
                ll = new ArrayList<>();
            }
        }
        return r;
    }
}

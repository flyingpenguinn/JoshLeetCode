import base.TreeNode;

import java.util.LinkedList;

/*
LC#101
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3


But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3


Note:
Bonus points if you could solve it both recursively and iteratively.
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return mirror(root.left, root.right);
    }

    boolean mirror(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null || n2 == null) {
            return false;
        }
        if (n1.val != n2.val) {
            return false;
        }
        return mirror(n1.left, n2.right) && mirror(n1.right, n2.left);
    }
}


class SymmetricTreeBfs {
    // bfs,put two nodes' children in reversed order
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root.left);
        q.offer(root.right);
        while (q.size() >= 2) {
            TreeNode n1 = q.poll();
            TreeNode n2 = q.poll();
            if (n1 == null && n2 == null) {
                continue;
            } else if ((n1 == null && n2 != null) || (n2 == null && n1 != null)) {
                return false;
            } else if (n1.val != n2.val) {
                return false;
            }
            q.offer(n1.left);
            q.offer(n2.right);
            q.offer(n1.right);
            q.offer(n2.left);
        }
        return q.isEmpty();
    }
}
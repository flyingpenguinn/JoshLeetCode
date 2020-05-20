import base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#653
Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:

Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True


Example 2:

Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
 */
public class TwoSumIvInputBst {

    // best: Olngn in space complexity, On in time. very similar to #1214...
    public boolean findTarget(TreeNode root, int k) {
        Deque<TreeNode> st1 = new ArrayDeque<>();
        Deque<TreeNode> st2 = new ArrayDeque<>();
        pushall(root, st1, true);
        pushall(root, st2, false);
        while (!st1.isEmpty() && !st2.isEmpty()) {
            TreeNode v1 = st1.peek();
            TreeNode v2 = st2.peek();
            if (v1 == v2) {
                break;
            } else if (v1.val + v2.val == k) {
                return true;
            } else if (v1.val + v2.val < k) {
                st1.pop();
                pushall(v1.right, st1, true);
            } else {
                st2.pop();
                pushall(v2.left, st2, false);
            }

        }
        return false;

    }

    void pushall(TreeNode n, Deque<TreeNode> st, boolean isleft) {
        while (n != null) {
            st.push(n);
            n = isleft ? n.left : n.right;
        }
    }

}

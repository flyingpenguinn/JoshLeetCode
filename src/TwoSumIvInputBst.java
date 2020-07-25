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
        TreeNode n = root;
        while (n != null) {
            st1.push(n);
            n = n.left;
        }
        n = root;
        while (n != null) {
            st2.push(n);
            n = n.right;
        }
        // output of st1 is sorted, st2 is reverse sorted
        while (!st1.isEmpty() && !st2.isEmpty() && st1.peek() != st2.peek()) {
            int t1 = st1.peek().val;
            int t2 = st2.peek().val;
            if (t1 + t2 == k) {
                return true;
            } else if (t1 + t2 < k) {
                TreeNode cur = st1.pop();
                cur = cur.right;
                while (cur != null) {
                    st1.push(cur);
                    cur = cur.left;
                }
            } else {
                TreeNode cur = st2.pop();
                cur = cur.left;
                while (cur != null) {
                    st2.push(cur);
                    cur = cur.right;
                }
            }
        }
        return false;
    }
}

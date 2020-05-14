import base.TreeNode;
import base.Trees;

import java.util.*;

/*
LC#272
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 */
public class ClosestBstValueII {
    // two stacks. when bst search fails, smaller stack has all the numbers < and top is the closest. bg has all the numbers > and top is the closest too
    // we then pop the closest and push in the pred/succ
    public List<Integer> closestKValues(TreeNode root, double t, int k) {
        // want the search to fail...
        if ((int) t == t) {
            t -= 0.001;
        }
        Deque<TreeNode> sm = new ArrayDeque<>();
        Deque<TreeNode> bg = new ArrayDeque<>();
        // when fail sm and bg are sorted
        TreeNode n = root;
        while (n != null) {
            if (n.val < t) {
                sm.push(n);
                n = n.right;
            } else {
                // no ==
                bg.push(n);
                n = n.left;
            }
        }

        List<Integer> r = new ArrayList<>();
        while (k > 0) {
            Integer vs = sm.isEmpty() ? null : sm.peek().val;
            Integer vb = bg.isEmpty() ? null : bg.peek().val;
            Double ds = vs == null ? null : Math.abs(vs - t);
            Double db = vb == null ? null : Math.abs(vb - t);
            if (db == null || (ds != null && ds <= db)) {
                TreeNode sn = sm.pop();
                r.add(sn.val);
                // prev value of sm,better than all in stack
                TreeNode snr = sn.left;
                while (snr != null) {
                    sm.push(snr);
                    //  System.out.println("adding sm " +snr.val);

                    snr = snr.right;
                }
            } else {
                TreeNode bn = bg.pop();
                r.add(bn.val);
                // next val of bg,better than all in stack
                TreeNode bnl = bn.right;
                while (bnl != null) {
                    bg.push(bnl);
                    bnl = bnl.left;
                }
            }
            k--;
        }
        return r;
    }
}

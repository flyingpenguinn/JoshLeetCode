import base.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
LC#637
Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
Note:
The range of node's value is in the range of 32-bit signed integer.
 */
public class AvgOfLevelsInBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> r = new ArrayList<>();
        if (root == null) {
            return r;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int lc = 1;
        int cc = 0;
        double lv = 0.0;
        while (!q.isEmpty()) {
            TreeNode top = q.poll();
            lv += top.val;
            if (top.left != null) {
                q.offer(top.left);
            }
            if (top.right != null) {
                q.offer(top.right);
            }
            cc++;
            if (lc == cc) {
                r.add(lv / lc);
                lc = q.size();
                cc = 0;
                lv = 0.0;
            }
        }
        return r;
    }
}

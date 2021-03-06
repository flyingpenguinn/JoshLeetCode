import base.TreeNode;

import java.util.*;

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
    private Map<Integer, Double> m = new HashMap<>();
    private Map<Integer, Integer> count = new HashMap<>();
    private int max = -1;

    public List<Double> averageOfLevels(TreeNode root) {
        dfs(root, 0);
        List<Double> r = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            int c = count.get(i);
            double v = m.get(i);
            double avg = v / c;
            r.add(avg);
        }
        return r;
    }

    void dfs(TreeNode n, int d) {
        if (n == null) {
            return;
        }
        max = Math.max(max, d);
        m.put(d, m.getOrDefault(d, 0.0) + n.val);
        count.put(d, count.getOrDefault(d, 0) + 1);
        dfs(n.left, d + 1);
        dfs(n.right, d + 1);
    }
}

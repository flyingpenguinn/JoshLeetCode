import base.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
LC#1305
Given two binary search trees root1 and root2.

Return a list containing all the integers from both trees sorted in ascending order.



Example 1:


Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]
Example 2:

Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
Output: [-10,0,0,1,2,5,7,10]
Example 3:

Input: root1 = [], root2 = [5,1,7,0,2]
Output: [0,1,2,5,7]
Example 4:

Input: root1 = [0,-10,10], root2 = []
Output: [-10,0,10]
Example 5:


Input: root1 = [1,null,8], root2 = [8,1]
Output: [1,1,8,8]


Constraints:

Each tree has at most 5000 nodes.
Each node's value is between [-10^5, 10^5].
 */
public class AllElementsInTwoBsts {
    int Max = 1000000;

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Deque<TreeNode> s1 = new ArrayDeque<>();
        Deque<TreeNode> s2 = new ArrayDeque<>();
        pushAllLeft(root1, s1);
        pushAllLeft(root2, s2);
        List<Integer> r = new ArrayList<>();
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int v1 = s1.isEmpty() ? Max : s1.peek().val;
            int v2 = s2.isEmpty() ? Max : s2.peek().val;
            if (v1 < v2) {
                r.add(v1);
                pushAllLeft(s1.pop().right, s1);
            } else {
                r.add(v2);
                pushAllLeft(s2.pop().right, s2);
            }
        }
        return r;
    }

    void pushAllLeft(TreeNode n, Deque<TreeNode> stack) {
        while (n != null) {
            stack.push(n);
            n = n.left;
        }

    }
}

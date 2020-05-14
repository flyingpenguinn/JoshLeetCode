/*

LC#1214

1214. Two Sum BSTs
User Accepted:1690
User Tried:1812
Total Accepted:1725
Total Submissions:2708
Difficulty:Medium
Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.



Example 1:



Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
Output: true
Explanation: 2 and 3 sum up to 5.
Example 2:



Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
Output: false


Constraints:

Each tree has at most 5000 nodes.
-10^9 <= target, node.val <= 10^9
 */

import base.TreeNode;
import base.Trees;

import java.util.ArrayDeque;
import java.util.Deque;

public class TwoSumBsts {


    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        // stack for 1 and 2. do in order in 1 and r-m-l in 2
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        pushAllTheWay(root1, stack1, true);
        pushAllTheWay(root2, stack2, false);
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            TreeNode t1 = stack1.peek();
            TreeNode t2 = stack2.peek();
            int sum = t1.val + t2.val;
            if (sum == target) {
                return true;
            } else if (sum < target) {
                // go to right but then exhaust its left subtree
                pushAllTheWay(stack1.pop().right, stack1, true);
            } else {
                // go to left, but exhaust its right subtree
                pushAllTheWay(stack2.pop().left, stack2, false);
            }
        }
        return false;
    }

    void pushAllTheWay(TreeNode n, Deque<TreeNode> stack, boolean isleft) {
        while (n != null) {
            stack.push(n);
            if (isleft) {
                n = n.left;
            } else {
                n = n.right;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new TwoSumBsts().twoSumBSTs(Trees.fromString("2,1,4"), Trees.fromString("1,0,3"), 5));
    }
}



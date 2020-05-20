import base.ArrayUtils;
import base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#654
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    /
     2  0
       \
        1
Note:
The size of the given array will be in the range [1,1000].
 */
public class MaxBinaryTree {
    // this is different in shape from heap which is a complete tree
    // complexity nlogn, worst case n^2
    public TreeNode constructMaximumBinaryTree(int[] a) {
        int n = a.length;
        return doc(a, 0, n - 1);
    }

    TreeNode doc(int[] a, int i, int j) {
        if (i > j) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        int maxi = -1;
        for (int k = i; k <= j; k++) {
            if (a[k] >= max) {
                max = a[k];
                maxi = k;
            }
        }
        TreeNode left = doc(a, i, maxi - 1);
        TreeNode right = doc(a, maxi + 1, j);
        TreeNode cur = new TreeNode(max);
        cur.left = left;
        cur.right = right;
        return cur;
    }

    public static void main(String[] args) {
        System.out.println(new MaxBinaryTreeStack().constructMaximumBinaryTree(ArrayUtils.read1d("3,2,1,6,0,5")));
    }
}

class MaxBinaryTreeStack {

    // https://leetcode.com/problems/maximum-binary-tree/discuss/106147/c-8-lines-on-log-n-map-plus-stack-with-binary-search
    public TreeNode constructMaximumBinaryTree(int[] a) {
        // mono stack from big to small. after popping smaller ones, the left tree of cur is the last popped up value
        // put cur into stack
        // next local biggest (but smaller than cur) will be cur's right child
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            TreeNode cur = new TreeNode(a[i]);
            while (!stack.isEmpty() && stack.peek().val < a[i]) {
                TreeNode prev = stack.pop();
                cur.left = prev; // we will set left at the last hence biggest element in the end
            }
            if (!stack.isEmpty()) {
                stack.peek().right = cur;
            }
            stack.push(cur);
        }
        while (stack.size() > 1) {
            stack.pop();
        }
        return stack.pop();
    }
}

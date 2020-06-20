import base.TreeNode;

/*
LC#270
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4
 */
public class ClosestBstValue {
    double min = 1e30;
    int minv = -1;

    public int closestValue(TreeNode n, double t) {
        dfs(n, t);
        return minv;
    }

    void dfs(TreeNode n, double t) {
        if (n == null) {
            return;
        }
        double diff = Math.abs(n.val - t);
        if (diff < min) {
            min = diff;
            minv = n.val;
        }
        if (diff < 0.000001) {
            return;
        } else if (n.val > t) {
            dfs(n.left, t);
        } else {
            dfs(n.right, t);
        }
    }
}

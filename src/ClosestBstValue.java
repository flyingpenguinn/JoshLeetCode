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
    Double mingap = null;
    int min = -1;

    public int closestValue(TreeNode root, double t) {
        dfs(root, t);
        return min;
    }

    void dfs(TreeNode n, double t) {
        if (n == null) {
            return;
        }
        double cgap = Math.abs(n.val - t);
        if (mingap == null || cgap < mingap) {
            mingap = cgap;
            min = n.val;
        }
        if (n.val < t) {
            dfs(n.right, t);
        } else if (n.val > t) {
            dfs(n.left, t);
        }
    }
}

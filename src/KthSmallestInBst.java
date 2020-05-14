import base.TreeNode;

/*
LC#230
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 */
public class KthSmallestInBst {
    // for follow up: modify tree to have node counts, then we can do binary search with O(h) complexity
    TreeNode v;

    public int kthSmallest(TreeNode root, int k) {

        dfs(root, k);
        return v.val;
    }

    int dfs(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return 0;
        }
        int lc = dfs(root.left, k);
        if (lc + 1 == k) {
            v = root;
            return lc + 1;
        } else {
            return lc + 1 + dfs(root.right, k - lc - 1);
        }
    }
}

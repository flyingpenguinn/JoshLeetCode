import base.TreeNode;

/*
LC#108
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
 */
public class ConvertSortedArrayToBst {
    // On because each node is visited only once
    public TreeNode sortedArrayToBST(int[] a) {
        return dot(a, 0, a.length - 1);
    }

    TreeNode dot(int[] a, int l, int u) {
        if (l > u) {
            return null;
        }
        if (l == u) {
            return new TreeNode(a[l]);
        }
        int mid = l + (u - l) / 2;
        TreeNode n = new TreeNode(a[mid]);
        TreeNode left = dot(a, l, mid - 1);
        TreeNode right = dot(a, mid + 1, u);
        n.left = left;
        n.right = right;
        return n;
    }
}

import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
 */
public class ConstructBinaryTreeFromPreAndInOrder {

    // just need length to dfs
    Map<Integer, Integer> im = new HashMap<>();

    public TreeNode buildTree(int[] io, int[] po) {
        if (po.length != io.length) {
            return null;
        }
        for (int i = 0; i < io.length; i++) {
            im.put(io[i], i);
        }
        return dob(po, 0, po.length, io, 0);
    }

    // dont really need upper
    TreeNode dob(int[] po, int pl, int len, int[] io, int il) {
        if (len == 0) {
            return null;
        }
        if (len == 1) {
            return new TreeNode(po[pl]);
        }
        int rv = po[pl + len - 1];
        TreeNode rt = new TreeNode(rv);
        Integer ior = im.get(rv);
        int leftlen = ior - il;
        int rightlen = len - 1 - leftlen;
        TreeNode left = dob(po, pl, leftlen, io, il);
        TreeNode right = dob(po, pl + leftlen, rightlen, io, ior + 1);
        rt.left = left;
        rt.right = right;
        return rt;
    }
}

import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorder {
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return doTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode doTree(int[] inorder, int il, int iu, int[] postorder, int pl, int pu) {
        if (il > iu) {
            return null;
        }
        if (il == iu) {
            return new TreeNode(inorder[il]);
        }
        int rootv = postorder[pu];
        int ir = map.get(rootv);
        TreeNode root = new TreeNode(rootv);
        int leftlen = ir - il;
        TreeNode left = doTree(inorder, il, ir - 1, postorder, pl, pl + leftlen - 1);
        TreeNode right = doTree(inorder, ir + 1, iu, postorder, pl + leftlen, pu - 1);
        root.left = left;
        root.right = right;
        return root;
    }


}

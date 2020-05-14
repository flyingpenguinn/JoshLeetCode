import base.TreeNode;

import java.util.*;

public class ConstructBSTfromPreOrder {
    public TreeNode bstFromPreorder(int[] preorder) {
        //next in preorder  is either immediate left, or immediate right, or certain ancestors right
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = new TreeNode(preorder[i]);
            if (stack.peek().val > preorder[i]) {
                stack.peek().left = node;
            } else {
                TreeNode last = stack.peek();
                while (!stack.isEmpty() && stack.peek().val < preorder[i]) {
                    last = stack.pop();
                }
                last.right = node;
            }
            stack.push(node);
        }
        return root;
    }

}

class BstFromPreorderSortBased {
    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode bstFromPreorder(int[] preorder) {
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        // bst
        Arrays.sort(inorder);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return doCreate(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode doCreate(int[] preorder, int pl, int pu, int[] inorder, int il, int iu) {
        if (pl == pu) {
            if (iu != il) {
                throw new IllegalArgumentException();
            }
            return new TreeNode(preorder[pl]);
        } else if (pl > pu) {
            return null;
        }
        int rootv = preorder[pl];
        TreeNode root = new TreeNode(rootv);
        int inroot = map.get(rootv);
        int leftlen = inroot - il;// not inroot-i1+1, but inroot-il, because we need to subtract root
        int leftstart = pl + 1;
        int leftend = pl + 1 + leftlen - 1;
        TreeNode left = doCreate(preorder, leftstart, leftend, inorder, il, inroot - 1);
        TreeNode right = doCreate(preorder, leftend + 1, pu, inorder, inroot + 1, iu);
        root.left = left;
        root.right = right;
        return root;
    }
}

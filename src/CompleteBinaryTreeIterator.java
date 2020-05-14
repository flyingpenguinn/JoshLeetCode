import base.TreeNode;

import java.util.LinkedList;

public class CompleteBinaryTreeIterator {
    LinkedList<TreeNode> pl = new LinkedList<>();
    LinkedList<TreeNode> cd = new LinkedList<>();
    TreeNode root = null;

    public CompleteBinaryTreeIterator(TreeNode root) {
        this.root = root;

        pl.add(root);
        while (true) {
            TreeNode top = pl.peekFirst();
            if (top.left != null) {
                cd.add(top.left);
            } else {
                // break at the parent with one child null
                break;
            }
            if (top.right != null) {
                cd.add(top.right);
                pl.pollFirst();
            } else {
                break;
            }
            if (pl.isEmpty()) {
                pl = cd;
                cd = new LinkedList<>();
            }
        }
    }

    public int insert(int v) {
        TreeNode nv = new TreeNode(v);
        TreeNode first = pl.peekFirst();
        if (first.left == null) {
            first.left = nv;
        } else {
            first.right = nv;
            pl.pollFirst();
        }
        cd.add(nv);
        if (pl.isEmpty()) {
            pl = cd;
            cd = new LinkedList<>();
        }
        return first.val;
    }

    public TreeNode get_root() {
        return root;
    }
}

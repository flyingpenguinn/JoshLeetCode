import base.TreeNode;
import base.Trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintBinaryTree {

    public List<List<String>> printTree(TreeNode root) {
        int h = dfs(root);
        int width = 1;
        for (int i = 2; i <= h; i++) {
            // key: we know the width given height
            width = 2 * width + 1;
        }
        List<List<String>> r = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            List<String> ri = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                ri.add("");
            }
            r.add(ri);
        }
        dfs2(root, r, 0, 0, width - 1);
        return r;
    }

    // we need an offset to tell us where to put this root.
    // note for right child it's offset + current root position
    private void dfs2(TreeNode root, List<List<String>> r, int h, int l, int u) {
        if (root == null) {
            return;
        }
        int mid = (l + u) / 2;
        r.get(h).set(mid, String.valueOf(root.val));
        dfs2(root.left, r, h + 1, l, mid - 1);
        dfs2(root.right, r, h + 1, mid + 1, u);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(dfs(root.left), dfs(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = Trees.fromString("1,2,5,3,null,null,null,4");
        System.out.println(new PrintBinaryTree().printTree(root));
    }
}

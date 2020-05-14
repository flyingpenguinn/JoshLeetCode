import base.TreeNode;
import base.Trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintBinaryTree {

    private static final String BLANK = "";

    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        List<List<String>> r = doPrint(root, height);
        Collections.reverse(r);
        return r;
    }

    private List<List<String>> doPrint(TreeNode root, int height) {
        List<List<String>> r = new ArrayList<>();
        if (height == 1) {
            List<String> single = new ArrayList<>();
            single.add(toString(root));
            r.add(single);
            return r;
        } else {
            List<List<String>> left = doPrint(root == null ? null : root.left, height - 1);
            List<List<String>> right = doPrint(root == null ? null : root.right, height - 1);
            List<String> cur = new ArrayList<>();
            for (int i = 0; i < left.size(); i++) {
                List<String> lefti = left.get(i);
                lefti.add(BLANK);
                List<String> righti = right.get(i);
                lefti.addAll(righti);
                r.add(lefti);
            }
            // just need righti's size....
            int halfSize = right.get(right.size() - 1).size();
            for (int i = 0; i < halfSize; i++) {
                cur.add(BLANK);
            }
            cur.add(toString(root));
            for (int i = 0; i < halfSize; i++) {
                cur.add(BLANK);
            }
            r.add(cur);
        }
        return r;
    }

    private String toString(TreeNode root) {
        return root == null ? BLANK : String.valueOf(root.val);
    }

    private int getHeight(TreeNode root) {
        return root == null ? 0 : Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = Trees.fromString("1,2,5,3,null,null,null,4");
        System.out.println(new PrintBinaryTree().printTree(root));
    }
}

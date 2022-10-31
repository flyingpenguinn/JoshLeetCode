import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class HeightOfBinaryTreeAfterSubtreeRemoval {
    // TODO: there are better approaches using longest and 2nd longest
    private class Node {
        int val;
        Node left;
        Node right;
        int lheight;
        int rheight;
        int height;

        Node div;
        boolean isdivleft;

        public Node(int val) {
            this.val = val;
        }
    }

    private Map<Integer, Node> vm = new HashMap<>();

    public int[] treeQueries(TreeNode root, int[] queries) {
        TreeNode fr = new TreeNode(-1);
        fr.left = root;
        dfs(fr, fr, true);

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int q = queries[i];
            Node qn = vm.get(q);
            res[i] = find(qn) - 2;
        }
        return res;
    }

    private int find(Node n) {
        int diff = n.height;
        while (n.val != -1) {
            Node div = n.div;
            int nheight = 0;
            if (n.isdivleft) {
                nheight = Math.max(div.lheight - diff, div.rheight);
            } else {
                nheight = Math.max(div.rheight - diff, div.lheight);
            }
            diff = Math.max(div.lheight, div.rheight) - nheight;
            if (diff == 0) {
                break;
            }
            n = div;
        }
        return vm.get(-1).height - diff;
    }


    private Node dfs(TreeNode n, TreeNode div, boolean isdivleft) {
        if (n == null) {
            Node nullnode = new Node(-1);
            return nullnode;
        }
        TreeNode leftdiv = n.right != null ? n : div;
        boolean flagleftdiv = n.right != null ? true : isdivleft;
        TreeNode rightdiv = n.left != null ? n : div;
        boolean flagrightdiv = n.left != null ? false : isdivleft;
        Node cur = new Node(n.val);
        vm.put(n.val, cur);
        Node left = dfs(n.left, leftdiv, flagleftdiv);
        Node right = dfs(n.right, rightdiv, flagrightdiv);

        cur.left = left;
        cur.right = right;
        cur.lheight = left.height;
        cur.rheight = right.height;
        cur.height = Math.max(cur.lheight, cur.rheight) + 1;
        cur.div = vm.get(div.val);
        cur.isdivleft = isdivleft;
        return cur;
    }
}

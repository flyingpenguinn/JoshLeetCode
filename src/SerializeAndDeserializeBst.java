import base.TreeNode;

import java.util.*;

public class SerializeAndDeserializeBst {
    // the way it deserializes uses bst property nicely, and the space complexity is O(lgn) better than O(n) for a general binary tree
    public class Codec {


        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            return dfs(root);
        }

        private String dfs(TreeNode n) {
            String str = String.valueOf(n.val);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(",");
            if (n.left != null) {
                sb.append(dfs(n.left));
            }
            if (n.right != null) {
                sb.append(dfs(n.right));
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            // keep possible nodes that is waiting for left to be populated in the stack
            Deque<TreeNode> st = new ArrayDeque<>();
            String[] ss = data.split(",");
            //    System.out.println(data+Arrays.toString(ss));
            TreeNode root = new TreeNode(Integer.valueOf(ss[0]));
            st.push(root);
            for (int i = 1; i < ss.length; i++) {
                TreeNode cur = new TreeNode(Integer.valueOf(ss[i]));
                if (cur.val <= st.peek().val) {
                    st.peek().left = cur;
                } else {
                    TreeNode pcur = null;
                    // any popped up nodes we are done with it so no use of keeping them
                    // note the while we want the highest node to be cur's parent
                    while (!st.isEmpty() && st.peek().val <= cur.val) {
                        pcur = st.pop();
                    }
                    pcur.right = cur;
                }
                st.push(cur);
            }
            return root;
        }
    }
}

class SerializeAndDeserializeBstAlt {
    // use stack to get next bigger. the right tree starts with next bigger so that we can catch it in O1 time
    private void dfs(TreeNode root, StringBuilder cur) {
        if (root == null) {
            return;
        }
        if (cur.length() > 0) {
            cur.append(",");
        }
        cur.append(root.val);
        dfs(root.left, cur);
        dfs(root.right, cur);
    }

    private TreeNode gen(String[] preorder, int i, int j, int[] nextb) {
        if (i > j) {
            return null;
        }
        int root = Integer.valueOf(preorder[i]);
        TreeNode proot = new TreeNode(root);
        int k = nextb[i] == -1 ? j + 1 : nextb[i];
        TreeNode left = gen(preorder, i + 1, k - 1, nextb);
        TreeNode right = gen(preorder, k, j, nextb);
        proot.left = left;
        proot.right = right;
        return proot;
    }

    private int[] getnext(String[] v) {
        Deque<Integer> st = new ArrayDeque<>();
        int n = v.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && Integer.valueOf(v[st.peek()])< Integer.valueOf(v[i])) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        return res;
    }

    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        dfs(root, res);
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.isEmpty()){
            return null;
        }
        String[] preorder = data.split(",");
        int[] nextb = getnext(preorder);
        return gen(preorder, 0, preorder.length - 1, nextb);
    }
}


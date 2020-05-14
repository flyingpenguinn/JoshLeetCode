import base.TreeNode;

import java.util.*;

public class SerializeAndDeserializeBst {
    // the way it deserializes uses lower/upper bound elegantly!
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            dfs(root, sb);
            return sb.toString();
        }

        private void dfs(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }
            sb.append(root.val);
            sb.append(",");
            dfs(root.left, sb);
            dfs(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            String[] ss = data.split(",");
            Deque<Integer> dq = new ArrayDeque<>();
            for (int i = 0; i < ss.length; i++) {
                dq.offerLast(Integer.valueOf(ss[i]));
            }
            return dos(dq, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        // <= on the left, > on the right
        private TreeNode dos(Deque<Integer> dq, int lower, int higher) {
            if (dq.isEmpty()) {
                return null;
            }
            int rv = Integer.valueOf(dq.peekFirst());
            // not belong here, should go to the other subtree
            if (rv < lower || rv > higher) {
                return null;
            }
            TreeNode root = new TreeNode(rv);
            dq.pollFirst();
            TreeNode left = dos(dq, lower, rv);
            TreeNode right = dos(dq, rv + 1, higher);
            root.left = left;
            root.right = right;
            return root;
        }
    }
}


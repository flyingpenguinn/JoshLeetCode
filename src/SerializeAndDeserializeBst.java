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


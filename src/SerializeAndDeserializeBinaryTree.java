import base.TreeNode;
import base.Trees;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/*
LC#297
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example:

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeAndDeserializeBinaryTree {

    private class Codec {

        // level order, null as #
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            LinkedList<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode top = q.poll();
                sb.append(toString(top));
                sb.append(",");
                if (top != null) {
                    q.offer(top.left);
                    q.offer(top.right);
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        // dont push a null node to queue.
        // use the fact that we alway show both children of a non null node to restore the tree using setleft flag
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            String[] sd = data.split(",");
            TreeNode root = toNode(sd[0]);
            LinkedList<TreeNode> q = new LinkedList<>();
            q.offer(root);
            boolean setLeft = false;
            for (int i = 1; i < sd.length; i++) {
                String cur = sd[i];
                TreeNode curNode = toNode(cur);
                if (!setLeft) {
                    q.peek().left = curNode;
                    setLeft = true;
                } else {
                    q.poll().right = curNode;
                    setLeft = false;
                }
                if (curNode != null) {
                    q.offer(curNode);
                }
            }
            return root;
        }

        private String toString(TreeNode n) {
            return n == null ? "#" : String.valueOf(n.val);
        }

        private TreeNode toNode(String s) {
            return "#".equals(s) ? null : new TreeNode(Integer.valueOf(s));
        }

    }
}


class CodecPreOrder {

    public String serialize(TreeNode root) {
        String r = dfs(root);
        return r;
    }

    String dfs(TreeNode n) {
        return n == null ? "#" : String.valueOf(n.val) + "," + dfs(n.left) + "," + dfs(n.right);
    }


    // if we mention all null nodes as "#" preorder can determine a tree uniquely
    // note we can't use "leftset" here becaue right isnt coming right after left...
    // but left must be right after toot since we always mention null children
    public TreeNode deserialize(String data) {
        String[] a = data.split(",");
        TreeNode root = tonode(a[0]);
        if (root == null) {
            return null;
        }
        Deque<TreeNode> st = new ArrayDeque<>();
        st.push(root);
        TreeNode last = root;
        for (int i = 1; i < a.length; i++) {
            TreeNode cur = tonode(a[i]);
            if (st.peek() == last) {
                st.peek().left = cur;
            } else {
                st.pop().right = cur;
            }
            if (cur != null) {
                st.push(cur);
            }
            last = cur;
        }
        return root;
    }

    TreeNode tonode(String s) {
        return "#".equals(s) ? null : new TreeNode(Integer.valueOf(s));
    }
}
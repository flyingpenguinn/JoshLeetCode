import base.TreeNode;
import base.Node;

import java.util.ArrayList;
import java.util.List;

/*
LC#431
Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree to get the original N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. Similarly, a binary tree is a rooted tree in which each node has no more than 2 children. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that an N-ary tree can be encoded to a binary tree and this binary tree can be decoded to the original N-nary tree structure.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See following example).

For example, you may encode the following 3-ary tree to a binary tree in this way:



Input: root = [1,null,3,2,4,null,5,6]
Note that the above is just an example which might or might not work. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.



Constraints:

The height of the n-ary tree is less than or equal to 1000
The total number of nodes is between [0, 10^4]
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 */
public class EncodeNrayToBinaryTree {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // first child is left child. other children are right children of the left child
    // the example shown by leetcode is incorrect...
    private class Codec {

        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode cur = new TreeNode(root.val);
            List<Node> ch = root.children;
            if (ch == null || ch.isEmpty()) {
                return cur;
            }

            TreeNode left = encode(ch.get(0));
            cur.left = left;
            TreeNode linkon = left;
            for (int i = 1; i < ch.size(); i++) {
                TreeNode child = encode(ch.get(i));
                linkon.right = child;
                linkon = child;
            }
            return cur;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            Node cur = new Node(root.val);
            cur.children = new ArrayList<>();
            if (root.left != null) {
                Node cl = decode(root.left);
                cur.children.add(cl);
                TreeNode pr = root.left.right;
                while (pr != null) {
                    Node cr = decode(pr);
                    cur.children.add(cr);
                    pr = pr.right;
                }
            }
            return cur;
        }
    }
}
